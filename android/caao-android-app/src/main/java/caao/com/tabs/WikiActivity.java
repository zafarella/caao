/**
 *  Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 17, 2011
 */
package caao.com.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import caao.com.R;

/**
 * Activity for handling the content of the wiki tab Contains the webview for
 * the whole page content. As a client user agent uses the chrome The JavaScript
 * is enabled in the webview.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.15 $
 */
public class WikiActivity extends Activity {
    /**
     * The web view for displaying the pages from wiki
     * @see WebView
     */
    WebView webview;

    /**
     * @param savedInstanceState Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.wiki_tab);
        this.webview = (WebView) findViewById(R.id.webview);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                getParent().setProgressBarVisibility(true); // TODO add correct
                // progress handling
                getParent().setProgress(progress * 1000);
            }
        });
        this.webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Toast.makeText(getParent(), "Error -> " + description,
                        Toast.LENGTH_SHORT).show();
            }
        });
        this.webview.loadUrl("http://en.m.wikipedia.org/");
    }

    /**
     * handle the back key press to point browser to go "back"
     *
     * @param keyCode int
     * @param event   KeyEvent
     * @return boolean android.view.KeyEvent$Callback#onKeyDown(int, KeyEvent)
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webview.canGoBack()) {
            this.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Points the Web view to go to specified URL this.URL
     *
     * @param pURL String
     */
    public void goToURL(String pURL) {
        if (null != pURL && "" != pURL)
            this.webview.loadUrl(pURL);
    }


    /**
     * @author zafar.khaydarov
     */
    @SuppressWarnings("unused")
    private class TabWebViewClient extends WebViewClient {
        /**
         * Method shouldOverrideUrlLoading.
         *
         * @param view WebView
         * @param url  String
         * @return boolean
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        /**
         * Method onCreate.
         */

        public void onCreate() {
            WikiActivity.this.webview.setWebViewClient(new TabWebViewClient());
        }

    }
}