package ro.ubb.c04remoting.common.domain;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class Client extends BaseEntity<Long> {

    private String name;

    private Long moneyAmount;
    private static final long serialVersionUID=-2907912165604440983L;

    public Client() {
    }

    public Client(Long id, String name, Long moneyAmount) {
        super(id);
        this.name = name;
        this.moneyAmount = moneyAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Client{" +"id="+getId()+
                " name='" + name + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
