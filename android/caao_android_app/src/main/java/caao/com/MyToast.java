/**
 *
 * Computer science department
 * Project: Context Aware Orginizer
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi
 * Web: cs.joensuu.fi/~zkhayda
 * Date: Apr 28, 2011
 */
package caao.com;

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
public final class MyToast {

    /**
     * Context the context
     *
     * @param pTheContext the message to be displayed
     * @param pTheMessage String how long the message should be displayed. true for long
     *                    and false for short
     * @param pHowLong    boolean
     */
    public MyToast(Context pTheContext, String pTheMessage, boolean pHowLong) {
        Toast.makeText(pTheContext, pTheMessage,
                pHowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    /**
     * Context the context
     *
     * @param pTheContext Context
     * @param pTheMessage String
     */
    public MyToast(Context pTheContext, String pTheMessage) {
        Toast.makeText(pTheContext, pTheMessage, Toast.LENGTH_LONG).show();
    }
}
