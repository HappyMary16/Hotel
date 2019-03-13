package ua.nure.borodin.hotel.dao;

public class DaoFactory {

    private static DaoFactory daoFactory = null;

    private ApplicationDao applicationDao;
    private OrderDao orderDao;
    private OrderRoomsDao orderRoomsDao;
    private RoomsDao roomsDao;
    private UserDao userDao;

    private DaoFactory() {
        loadDaos();
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    private void loadDaos() {
        applicationDao = ApplicationDao.getInstance();
        orderDao = OrderDao.getInstance();
        orderRoomsDao = OrderRoomsDao.getInstance();
        roomsDao = RoomsDao.getInstance();
        userDao = UserDao.getInstance();

    }

    public ApplicationDao getApplicationDao() {
        return applicationDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderRoomsDao getOrderRoomsDao() {
        return orderRoomsDao;
    }

    public RoomsDao getRoomsDao() {
        return roomsDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
