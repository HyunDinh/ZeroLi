package startup.zeroli.controller.mainController;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public static final String CARTPAGE_REDIRECT = "cartPage";
    public static final String CHECKOUTPAGE_REDIRECT = "checkoutPage";

    // redirect to each servlets
    public static final String HOMEPAGE_SERVLET = "/" + HOMEPAGE_REDIRECT;
    public static final String LOGINPAGE_SERVLET = "/" + LOGINPAGE_REDIRECT;
    public static final String PRODUCTPAGE_SERVLET = "/" + PRODUCTPAGE_REDIRECT;
    public static final String CARTPAGE_SERVLET = "/" + CARTPAGE_REDIRECT;
    public static final String CHECKOUTPAGE_SERVLET = "/" + CHECKOUTPAGE_REDIRECT;
    // main?action=

    // post
    public static final String ACTION_LOGIN = "login";

    // product review
    public static final String ACTION_ADDREVIEW = "add-review";
    public static final String ACTION_UPDATEREVIEW = "update-review";
    public static final String ACTION_DELETEREVIEW = "delete-review";

    //cart
    public static final String ACTION_ADDITEMS = "add-items";
    public static final String ACTION_INCREASE_QUANTITY = "increase-quantity";
    public static final String ACTION_DECREASE_QUANTITY = "decrease-quantity";
    public static final String ACTION_APPLY_VOUCHER = "apply-voucher";
    public static final String ACTION_UPDATE_SELECTION = "update-selection";
//    public static final String ACTION_UPDATEITEMS = "update-items";
//    public static final String ACTION_DELETEITEMS = "delete-items";
    public static final String ACTION_CHECKOUT = "confirm-checkout";
    
    //get ACTION_SEARCHPRODUCT
    public static final String ACTION_SEARCHPRODUCT = "search-product";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // xu li du lieu dau vao
        String action = request.getParameter("action").trim();
//        ErrDialog.showError("Main: " + action);
        switch (action) {
            case ACTION_LOGIN ->
                request.getRequestDispatcher(LOGINPAGE_REDIRECT).forward(request, response);
            case ACTION_ADDREVIEW, ACTION_UPDATEREVIEW, ACTION_DELETEREVIEW -> {
                request.getRequestDispatcher(PRODUCTPAGE_REDIRECT).forward(request, response);
            }
            case ACTION_ADDITEMS, ACTION_UPDATE_SELECTION, ACTION_INCREASE_QUANTITY, ACTION_DECREASE_QUANTITY, ACTION_APPLY_VOUCHER-> {
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
//        ErrDialog.showError("Main: " + action);
        switch (action) {
            case HOMEPAGE_REDIRECT, LOGINPAGE_REDIRECT, PRODUCTPAGE_REDIRECT, CARTPAGE_REDIRECT, CHECKOUTPAGE_REDIRECT ->
                request.getRequestDispatcher(action).forward(request, response);
            case ACTION_SEARCHPRODUCT ->
                request.getRequestDispatcher(PRODUCTPAGE_REDIRECT).forward(request, response);
            default ->
                response.sendRedirect("errorAtMainController.jsp");
        }
    }

}
