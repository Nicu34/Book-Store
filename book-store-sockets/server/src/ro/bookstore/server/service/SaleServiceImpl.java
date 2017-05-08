package ro.bookstore.server.service;

import ro.bookstore.common.domain.Sale;
import ro.bookstore.server.repository.Repository;

import java.util.concurrent.ExecutorService;

/**
 * Created by nicu on 4/24/2017.
 */
public class SaleServiceImpl extends ServiceImpl<Long, Sale> {
    public SaleServiceImpl(Repository<Long, Sale> repository, ExecutorService executorService) {
        super(repository, executorService);
    }
}
