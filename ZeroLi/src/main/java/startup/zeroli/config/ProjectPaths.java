package startup.zeroli.config;

//import startup.zeroli.controller.redirectController.CanvasControllerServlet;
import startup.zeroli.controller.mainController.MainControllerServlet;
import startup.zeroli.model.Canvas;

/**
 *
 * @author LENOVO
 */
public class ProjectPaths {
    
    // JPS pages -> main controllerServlet
    public static final String PREFIX_WEB_PATH = "/ZeroLi";
    public static final String HREF_TO_MAINCONTROLLER = PREFIX_WEB_PATH + "/main?action=";
//    public static final String HREF_TO_CANVASCONTROLLER = PREFIX_WEB_PATH + "/canvasPage?action=";
    public static final String HREF_TO_HOMEPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.HOMEPAGE_REDIRECT;
    public static final String HREF_TO_LOGINPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.LOGINPAGE_REDIRECT;
    public static final String HREF_TO_CANVASPAGE = HREF_TO_MAINCONTROLLER + MainControllerServlet.CANVASPAGE_REDIRECT;


    public static final String JSP_PATH = "/WEB-INF/view/pages/";
    public static final String JSP_HOMEPAGE_PATH = JSP_PATH + "homePage/homePage.jsp";
    public static final String JSP_LOGINPAGE_PATH = JSP_PATH + "loginPage/loginPage.jsp";
    public static final String JSP_CANVASPAGE_PATH = JSP_PATH + "canvas/canvasPage.jsp";
    public static final String JSP_CANVASDETAILSPAGE_PATH = JSP_PATH + "canvas/CanvasPageDetails.jsp";

    
    
    
    
    
}
