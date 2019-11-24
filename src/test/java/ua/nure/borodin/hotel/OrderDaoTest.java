//package ua.nure.borodin.hotel;
//
//import org.dbunit.DBTestCase;
//import org.dbunit.PropertiesBasedJdbcDatabaseTester;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
//import org.xml.sax.InputSource;
//import ua.nure.borodin.hotel.dao.OrderDao;
//import ua.nure.borodin.hotel.helpers.PropertyHelper;
//import ua.nure.borodin.hotel.model.entity.Order;
//import ua.nure.borodin.hotel.model.entity.OrderStatus;
//
//import java.io.FileInputStream;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//
//public class OrderDaoTest extends DBTestCase {
//
//    private OrderDao orderDao;
//
//    public OrderDaoTest(String name) {
//        super(name);
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, PropertyHelper.getInstance().getJdbcURL());
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, PropertyHelper.getInstance().getDbUserLogin());
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, PropertyHelper.getInstance().getDbUserPassword());
//        orderDao = OrderDao.getInstance();
//    }
//
//    public void testInsert() {
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-15");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Order order = new Order(1L, date, date, 100, OrderStatus.OPENED, Arrays.asList(1L));
//
//        orderDao.insert(order);
//
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-14");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        order.setTo(date);
//        order.setFrom(date);
//        assertEquals(order, orderDao.findOrders(Arrays.asList(String.valueOf(order.getId()))).get(0));
//    }
//
//    public void testUpdate() {
//        Order order = orderDao.findOrder(1);
//        Date date = null;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-15");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        orderDao.update(order.getId(), date, OrderStatus.OPENED);
//
//        order.setSetBill(date);
//        order.setStatus(OrderStatus.OPENED);
//
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-14");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        order.setSetBill(date);
//        assertEquals(order, orderDao.findOrder(1));
//    }
//
//    @Override
//    protected IDataSet getDataSet() throws Exception {
//        return new FlatXmlDataSetBuilder().build(new InputSource(new FileInputStream("..\\Hotel\\src\\test\\resources\\order-data.xml")));
//    }
//}
