package pe.com.empresa.rk.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.empresa.rk.support.WhereParams;
import pe.com.empresa.rk.view.model.ModuloFilterViewModel;
import pe.com.empresa.rk.view.model.ModuloResultViewModel;
import pe.com.empresa.rk.view.model.ModuloViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josediaz on 25/11/2016.
 */
@Repository
public class ModuloJdbcRepository implements ModuloRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuloJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    ModuloJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String SEARCH_MODULES_VISIBLE_PER_USER = "select \n" +
            "  modprt.Codigo as \"codigoPadre\", modprt.Nombre as \"nombrePadre\",\n" +
            "  modprt.Url as \"urlPadre\",\n" +
            "  M.IdModulo AS \"IdModulo\", \n" +
            "  M.Codigo AS \"codigo\",\n" +
            "  M.HelpUrl AS \"helpUrl\",\n" +
            "  M.EtiquetaMenu AS \"etiquetaMenu\",\n" +
            "  M.Nombre AS \"nombre\",\n" +
            "  M.URL AS \"url\"\n" +
            "from Modulo m\n" +
            "inner join Modulo modprt on M.IdParent = modprt.IdModulo\n" +
            "inner join Permiso p on p.IdModulo = M.IdModulo\n" +
            "inner join Rol r on r.IdRol = p.IdRol\n" +
            "inner join UsuarioRol ur on ur.IdRol = r.IdRol\n" +
            "inner join Usuario u on u.IdUsuario = Ur.IdUsuario\n" +
            "where 1=1\n" +
            "and modprt.Visible = 1\n" +
            "and M.Visible = 1\n" +
            "and p.TipoPermiso != 'N'\n" +
            "and u.cuentaUsuario = :cuentaUsuario \n" +
            "group by  modprt.Codigo , modprt.Nombre , modprt.Url, M.IdModulo, M.Codigo, M.HelpUrl, M.EtiquetaMenu, M.Nombre, M.URL , modprt.Orden, M.Orden \n" +
            "order by modprt.Orden, modprt.Codigo, m.Orden";
    
    private static final String GET_ACCES_MODULES_PER_USER= "SELECT distinct \n"+ 
    		 " M.IdModulo,M.Codigo, M.HelpUrl,  M.EtiquetaMenu, M.Nombre, M.Url, M.Orden,M.ImageClass, \n"+
    		 " COALESCE(n.TieneSubModulo,0) tieneSubMenu  \n"+
    		 " FROM Modulo M \n"+
    		 " LEFT JOIN (SELECT DISTINCT 1 TieneSubModulo,a.IdModulo \n"+ 
    		 "				FROM Modulo a \n"+
    		 "				INNER JOIN (SELECT IdParent FROM Modulo) b ON a.IdModulo = b.IdParent \n"+ 
    		 "				) n	ON m.IdModulo = n.IdModulo  \n"+
    		 " LEFT JOIN Accion A ON A.IdModulo = M.IdModulo \n"+
    		 " LEFT JOIN Autorizacion P ON P.IdAccion = A.IdAccion \n"+
    		 " LEFT JOIN Rol R ON R.IdRol = P.IdRol \n"+
    		 " LEFT JOIN UsuarioRol UR ON UR.IdRol = R.IdRol \n"+
    		 " LEFT JOIN Usuario U ON UR.IdUsuario=U.IdUsuario \n"+
    		 " WHERE (U.CuentaUsuario =:cuentaUsuario and A.Nombre='Acceder' and P.Autorizado=1 and M.Visible=1 and UR.PorDefecto=1) \n"+
    		 " or M.tieneSubModulo=1 \n"+
    		 " GROUP BY  M.IdModulo,M.Codigo, M.HelpUrl,  M.EtiquetaMenu, M.Nombre, M.Url,n.TieneSubModulo,M.ImageClass \n"+
    		 " ,P.Autorizado,M.Codigo ,M.Orden \n"+
    		 "  order by M.Codigo ";
    
    private static final String SEARCH_ROLES_AVAILABLE_PER_USER = "select \n" +
            "  r.Nombre AS \"rolName\",\n" +
            "  u.IdUsuario AS \"idUsuario\",\n" +
            "  u.IdEmpleado AS \"idEmpleado\" \n" +
            "from Usuario u \n" +
            "inner join UsuarioRol ur on u.IdUsuario = ur.IdUsuario\n" +
            "inner join Rol r on r.IdRol = ur.IdRol\n" +
            "where 1=1\n" +
            "and u.cuentaUsuario = :cuentaUsuario";


    @Override
    public List<ModuloViewModel> getModulosPermitidosPorUsuario(String cuentaUsuario) {
        LOGGER.info("Buscando modulos visibles con permisos por cuenta de Usuario: {}", cuentaUsuario);

        //List<ModuloPadreViewModel> searchResults =encontrarModulos(cuentaUsuario, SEARCH_MODULES_VISIBLE_PER_USER);
        List<ModuloViewModel> searchResults =encontrarModulos(cuentaUsuario, GET_ACCES_MODULES_PER_USER);
        

        return searchResults;
    }

    private List<ModuloViewModel> encontrarModulos(String cuentaUsuario, String query){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("cuentaUsuario", cuentaUsuario);

        List<ModuloViewModel> searchResults = jdbcTemplate.query(query,
                queryParams,
                new BeanPropertyRowMapper<>(ModuloViewModel.class)
        );

        LOGGER.info("Encontro {} modulos ", searchResults.size());

        return searchResults;
    }

	@Override
	public List<ModuloResultViewModel> buscarModulos(ModuloFilterViewModel filterViewModel) {
		WhereParams params = new WhereParams();
        String sql = generarBuscarModulos(filterViewModel, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<ModuloResultViewModel>(ModuloResultViewModel.class));

	}

	private String generarBuscarModulos(ModuloFilterViewModel filterViewModel, WhereParams params) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select m.IdModulo, "); 
		sql.append("  case when (m.IdParent is null) then '' else  (select mh.codigo from Modulo mh where mh.IdModulo=m.IdParent)"); 
		sql.append("   end  as codigoPadre , ");
		sql.append("  m.Codigo as codigo, m.Nombre as nombre ,m.Orden as orden,m.EtiquetaMenu as etiquetaMenu, ");
		sql.append("  case when (m.Visible=1) then 'Visible' else 'No Visible' end as visible ");
		sql.append("  from Modulo m ");
		sql.append(" WHERE 1=1 ");
		sql.append(params.filter(" AND UPPER(m.Codigo) LIKE  UPPER ('%'+ :codigo +'%') ",filterViewModel.getCodigo()));
		sql.append(params.filter(" AND UPPER(m.Nombre) LIKE  UPPER ('%'+ :nombre +'%') ",filterViewModel.getNombre()));
		sql.append(params.filter(" AND m.Visible = :visible ",filterViewModel.getVisible()));
	     
		return sql.toString();
	}


}
