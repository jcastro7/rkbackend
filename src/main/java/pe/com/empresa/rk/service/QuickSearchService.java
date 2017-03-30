package pe.com.empresa.rk.service;

import java.util.List;

/**
 * Created by josediaz on 6/01/2017.
 * @param <M>
 * @param <F>
 * @param <R>
 * @param <N>
 
 */
public interface QuickSearchService<R,Q> {

    List<R> quickSearch(Q quickFilter);
    
    
}
