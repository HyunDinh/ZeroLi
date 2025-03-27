package startup.zeroli.config;

import startup.zeroli.controller.mainController.MainControllerServlet;

/**
 *
 * @author LENOVO
 */
public class ProjectPaths {

    // JPS pages -> main controllerServlet
    public static final String PREFIX_WEB_PATH = "/ZeroLi";
    public static final String HREF_TO_MAINCONTROLLER = PREFIX_WEB_PATH + "/main?action=";

    public static final String HREF_TO_PROFILEPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.PROFILEPAGE_REDIRECT;
    public static final String HREF_TO_HOMEPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.HOMEPAGE_REDIRECT;
    public static final String HREF_TO_FRIENDPAGE = "#";
    public static final String HREF_TO_LOGINPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.LOGINPAGE_REDIRECT;
    public static final String HREF_TO_PRODUCTPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.PRODUCTPAGE_REDIRECT;
    public static final String HREF_TO_BOOKPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.BOOKPAGE_REDIRECT;
    public static final String HREF_TO_SERVICE = HREF_TO_MAINCONTROLLER + MainControllerServlet.SERVICE_REDIRECT;
    public static final String HREF_TO_CANVASPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.CANVASPAGE_REDIRECT;
    public static final String HREF_TO_CARTPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.CARTPAGE_REDIRECT;
    public static final String HREF_TO_CHECKOUTPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.CHECKOUTPAGE_REDIRECT;

    // RedirectServlets -> JSP pages
    public static final String JSP_PATH = "/WEB-INF/view/pages/";
    public static final String JSP_PROFILEPAGE_PATH = JSP_PATH + "profilePage/profilePage.jsp";
    public static final String JSP_HOMEPAGE_PATH = JSP_PATH + "homePage/homePage.jsp";
    public static final String JSP_LOGINPAGE_PATH = JSP_PATH + "loginPage/loginPage.jsp";
    public static final String JSP_PRODUCTPAGE_PATH = JSP_PATH + "productPage/productPage.jsp";
    public static final String JSP_PRODUCTDETAILSPAGE_PATH = JSP_PATH + "productPage/productDetailsPage.jsp";
    public static final String JSP_BOOKPAGE_PATH = JSP_PATH + "bookPage/bookPage.jsp";
    public static final String JSP_BOOKDETAILPAGE_PATH = JSP_PATH + "bookPage/bookDetail.jsp";
    public static final String JSP_CANVASPAGE_PATH = JSP_PATH + "canvas/canvasPage.jsp";
    public static final String JSP_CANVASDETAILSPAGE_PATH = JSP_PATH + "canvas/CanvasPageDetails.jsp";
    public static final String JSP_SERVICE_PATH = JSP_PATH + "servicePage/servicePage.jsp";
    public static final String JSP_CARTPAGE_PATH = JSP_PATH + "productPage/cart.jsp";
    public static final String JSP_CHECKOUTPAGE_PATH = JSP_PATH + "productPage/checkout.jsp";

}
