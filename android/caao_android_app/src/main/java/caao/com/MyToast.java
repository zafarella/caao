/**
 *
 * Computer science department
 * Project: Context Aware Orginizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 28, 2011
 */
package main.java.caao.com;

import android.content.Context;
import android.widget.Toast;

/**
 * Class used for simplify the code. Class displays the android style short
 * messages called Toast. There is only constructor of it to satisfy the needs.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.1 $
 * @see Toast
 */
public class MyToast {

    /**
     * Context the context
     *
     * @param pTheContext the message to be displayed
     * @param pTheMesage  String how long the message should be displayed. true for long
     *                    and false for short
     * @param pHowLong    boolean
     */
    public MyToast(Context pTheContext, String pTheMesage, boolean pHowLong) {
        Toast.makeText(pTheContext, pTheMesage,
                pHowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * Context the context
     *
     * @param pTheContext Context
     * @param pTheMesage  String
     */
    public MyToast(Context pTheContext, String pTheMesage) {
        Toast.makeText(pTheContext, pTheMesage, Toast.LENGTH_LONG).show();
    }
}
