package startup.zeroli.controller.mainController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import startup.zeroli.utils.ErrDialog;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "MainControllerServlet", urlPatterns = {"/main"})
public class MainControllerServlet extends HttpServlet {

    // display servlet list
    public static final String HOMEPAGE_REDIRECT = "homePage";
    public static final String LOGINPAGE_REDIRECT = "loginPage";
    public static final String PRODUCTPAGE_REDIRECT = "productPage";
    public static final String BOOKPAGE_REDIRECT = "bookPage";
    public static final String SERVICE_REDIRECT = "servicePage";
    public static final String CANVASPAGE_REDIRECT = "CanvasPage";
    public static final String PROFILEPAGE_REDIRECT = "profilePage";
    public static final String CARTPAGE_REDIRECT = "cartPage";
    public static final String CHECKOUTPAGE_REDIRECT = "checkoutPage";

    // redirect to each servlets
    public static final String HOMEPAGE_SERVLET = "/" + HOMEPAGE_REDIRECT;
    public static final String LOGINPAGE_SERVLET = "/" + LOGINPAGE_REDIRECT;
    public static final String PRODUCTPAGE_SERVLET = "/" + PRODUCTPAGE_REDIRECT;
    public static final String BOOKPAGE_SERVLET = "/" + BOOKPAGE_REDIRECT;
    public static final String SERVICEPAGE_SERVLET = "/" + SERVICE_REDIRECT;
    public static final String CANVASPAGE_SERVLET = "/" + CANVASPAGE_REDIRECT;
    public static final String PROFILEPAGAE_SERVLET = "/" + PROFILEPAGE_REDIRECT;
    public static final String CARTPAGE_SERVLET = "/" + CARTPAGE_REDIRECT;
    public static final String CHECKOUTPAGE_SERVLET = "/" + CHECKOUTPAGE_REDIRECT;

    // main?action=
    // post (Action)
    public static final String ACTION_LOGIN = "login";
    public static final String ACTION_ADDREVIEW = "add-review"; // product
    public static final String ACTION_UPDATEREVIEW = "update-review"; // product 
    public static final String ACTION_DELETEREVIEW = "delete-review"; // product
    public static final String ACTION_UPDATE_AVATAR_USER = "update-avatar-user"; // profile
    public static final String ACTION_ADDITEMS = "add-items"; // cart
    public static final String ACTION_INCREASE_QUANTITY = "increase-quantity"; // cart
    public static final String ACTION_DECREASE_QUANTITY = "decrease-quantity"; // cart
    public static final String ACTION_CHECKBOX_ITEM_SHOPPINGCART = "select-item-in-checkbox-shoppingcart";
    public static final String ACTION_APPLY_VOUCHER = "apply-voucher"; // cart
    public static final String ACTION_UPDATE_SELECTION = "update-selection"; // cart
    public static final String ACTION_CHECKOUT = "confirm-checkout"; // checkOut

    // get (Action)
    public static final String ACTION_SEARCHPRODUCT = "search-product"; // product
    public static final String ACTION_VIEW_BOOK = "viewBook"; // book
    public static final String ACTION_VIEW_BOOKDETAIL = "viewBookDetail"; // book
    public static final String ACTION_FAVORITE_BOOK = "favoriteBook"; // book
    public static final String ACTION_SEARCH_CANVAS = "searchCanvas"; // canvas

    // session attribute
    public static final String SS_ATTR_ERROR = "aaaaaa";
    public static final String SS_ATTR_USER = "aaaaab";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // xu li du lieu dau vao
        String action = request.getParameter("action").trim();
        
        switch (action) {
            case ACTION_LOGIN ->
                request.getRequestDispatcher(LOGINPAGE_REDIRECT).forward(request, response);
            case ACTION_ADDREVIEW, ACTION_UPDATEREVIEW, ACTION_DELETEREVIEW ->
                request.getRequestDispatcher(PRODUCTPAGE_REDIRECT).forward(request, response);
            case ACTION_UPDATE_AVATAR_USER ->
                request.getRequestDispatcher(PROFILEPAGE_REDIRECT).forward(request, response);
            case ACTION_ADDITEMS, ACTION_UPDATE_SELECTION, ACTION_INCREASE_QUANTITY, ACTION_DECREASE_QUANTITY, ACTION_APPLY_VOUCHER, ACTION_CHECKBOX_ITEM_SHOPPINGCART -> {
                request.getRequestDispatcher(CARTPAGE_REDIRECT).forward(request, response);
            }
            case ACTION_CHECKOUT ->
                request.getRequestDispatcher(CHECKOUTPAGE_REDIRECT).forward(request, response);
            default ->
                response.sendRedirect("errorAtMainController.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // chuyen huong / tac vu don gian
        String action = request.getParameter("action");

        switch (action) {
            case HOMEPAGE_REDIRECT, LOGINPAGE_REDIRECT, PRODUCTPAGE_REDIRECT, SERVICE_REDIRECT, BOOKPAGE_REDIRECT, CANVASPAGE_REDIRECT, PROFILEPAGE_REDIRECT, CARTPAGE_REDIRECT, CHECKOUTPAGE_REDIRECT ->
                request.getRequestDispatcher(action).forward(request, response);
            case ACTION_SEARCHPRODUCT ->
                request.getRequestDispatcher(PRODUCTPAGE_REDIRECT).forward(request, response);
            case ACTION_VIEW_BOOK, ACTION_VIEW_BOOKDETAIL, ACTION_FAVORITE_BOOK ->
                request.getRequestDispatcher(BOOKPAGE_REDIRECT).forward(request, response);
            case ACTION_SEARCH_CANVAS ->
                request.getRequestDispatcher(CANVASPAGE_REDIRECT).forward(request, response);
            default ->
                response.sendRedirect("errorAtMainController.jsp");
        }
    }

}
