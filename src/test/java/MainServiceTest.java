import com.testtask.robofinance.domain.Customer;
import com.testtask.robofinance.repos.AddressRepo;
import com.testtask.robofinance.repos.CustomerRepo;
import com.testtask.robofinance.service.MainService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MainServiceTest {

    @Test
    public void getAllCustomers_OneCustomer_ListFromOneCustomer() {

        CustomerRepo customerRepoMock = Mockito.mock(CustomerRepo.class);
        AddressRepo addressRepoMock = Mockito.mock(AddressRepo.class);
        MainService mainService = new MainService(addressRepoMock, customerRepoMock);

        Iterable<Customer> customers = new ArrayList<>();
        ((ArrayList<Customer>) customers).add(new Customer());

        Mockito.when(customerRepoMock.findAll()).thenReturn(customers);

        List<Customer> actual = mainService.getAllCustomers();
        int size = actual.size();

        Assert.assertNotNull(actual);
        Assert.assertEquals(1,size);
    }

    @Test(expected = Exception.class)
    public void getAllCustomers_NullInIterable_Exception() {

        CustomerRepo customerRepoMock = Mockito.mock(CustomerRepo.class);
        AddressRepo addressRepoMock = Mockito.mock(AddressRepo.class);
        MainService mainService = new MainService(addressRepoMock, customerRepoMock);

        Iterable<Customer> customers = new ArrayList<>();
        ((ArrayList<Customer>) customers).add(null);

        Mockito.when(customerRepoMock.findAll()).thenReturn(customers);

        List<Customer> actual = mainService.getAllCustomers();
    }



}