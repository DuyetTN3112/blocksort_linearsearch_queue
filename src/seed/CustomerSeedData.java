package seed;

import model.Customer;
import service.CustomerService;

public class CustomerSeedData {
    
    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            // Khách hàng tại Hà Nội
            new Customer("Nguyễn Văn An", "123 Phố Huế, Hai Bà Trưng, Hà Nội"),
            new Customer("Trần Thị Bình", "456 Đường Láng, Đống Đa, Hà Nội"),
            new Customer("Lê Minh Cường", "789 Giảng Võ, Ba Đình, Hà Nội"),
            new Customer("Phạm Thị Dung", "321 Trần Phú, Hoàn Kiếm, Hà Nội"),
            new Customer("Hoàng Văn Em", "654 Nguyễn Du, Hai Bà Trưng, Hà Nội"),
            
            // Khách hàng tại TP.HCM
            new Customer("Võ Thị Phương", "147 Lê Lợi, Quận 1, TP.HCM"),
            new Customer("Đặng Văn Quang", "258 Nguyễn Trãi, Quận 5, TP.HCM"),
            new Customer("Bùi Thị Hoa", "369 Phan Xich Long, Phú Nhuận, TP.HCM"),
            new Customer("Lý Văn Minh", "741 Võ Văn Kiệt, Quận 6, TP.HCM"),
            new Customer("Trương Thị Lan", "852 Lê Thị Rieng, Quận 12, TP.HCM"),
            
            // Khách hàng các tỉnh khác
            new Customer("Đoàn Văn Thành", "963 Trần Hưng Đạo, Hải Châu, Đà Nẵng"),
            new Customer("Nguyễn Thị Oanh", "159 Lê Lợi, Thành phố Huế, Thừa Thiên Huế"),
            new Customer("Phan Văn Tân", "357 Nguyễn Đình Chiểu, Phan Thiết, Bình Thuận"),
            new Customer("Lê Thị Mai", "468 Trưng Vương, Thành phố Việt Trì, Phú Thọ"),
            new Customer("Vũ Văn Long", "579 Lê Dudu, Quận Ngô Quyền, Hải Phòng")
        };
    }
    
    public static void loadSampleCustomers() {
        Customer[] customers = getSampleCustomers();
        for (Customer customer : customers) {
            CustomerService.addCustomer(customer);
        }
    }
}
