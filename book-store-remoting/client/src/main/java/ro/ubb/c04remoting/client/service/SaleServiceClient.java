package ro.ubb.c04remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.c04remoting.common.domain.Sale;
import ro.ubb.c04remoting.common.service.SaleService;

import java.util.Optional;
import java.util.Set;

/**
 * Created by MuresanN on 5/8/2017.
 */
@Component
public class SaleServiceClient implements SaleService {

    @Autowired
    private SaleService saleService;

    @Override
    public Optional<Sale> add(Sale entity) {
        return saleService.add(entity);
    }

    @Override
    public Optional<Sale> delete(Long aLong) {
        return saleService.delete(aLong);
    }

    @Override
    public Optional<Sale> update(Sale entity) {
        return saleService.update(entity);
    }

    @Override
    public Set<Sale> getAll() {
        return saleService.getAll();
    }

    @Override
    public Optional<Sale> findOne(Long aLong) {
        return saleService.findOne(aLong);
    }
}
