package ro.ubb.c04remoting.server.service;

import ro.ubb.c04remoting.common.domain.Sale;
import ro.ubb.c04remoting.server.repository.Repository;

/**
 * Created by nicu on 4/24/2017.
 */
public class SaleServiceImpl extends ServiceImpl<Long, Sale> {
    public SaleServiceImpl(Repository<Long, Sale> repository) {
        super(repository);
    }
}
