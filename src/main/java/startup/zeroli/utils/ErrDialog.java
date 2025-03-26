package startup.zeroli.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class ErrDialog {

    public static void showError(String errorMessage) {
        String fileName = new Throwable().getStackTrace()[1].getFileName();
        JOptionPane.showMessageDialog(null, errorMessage, "ERROR: " + fileName, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showFullError(String errorMessage) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[2]; // Lấy stack trace của phương thức gọi
        
        String fileName = caller.getFileName();      // Tên file chứa lỗi
        String className = caller.getClassName();    // Tên class chứa lỗi
        String methodName = caller.getMethodName();  // Tên phương thức chứa lỗi
        int lineNumber = caller.getLineNumber();     // Số dòng bị lỗi
        
        String fullMessage = String.format(
            "Error at :\nFile: %s\nClass: %s\nMethod: %s\nLine: %d\n\nDetails : %s",
            fileName, className, methodName, lineNumber, errorMessage
        );

        JOptionPane.showMessageDialog(null, fullMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

}
