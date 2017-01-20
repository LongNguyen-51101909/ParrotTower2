package jp.co.parrottower;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
//import com.google.ads.AdRequest;
//import com.google.ads.AdSize;
//import com.google.ads.AdView;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

public class parrottower
  extends Cocos2dxActivity
{
  private static Cocos2dxActivity me = null;
  private final int MP = -1;
  private final int WC = -2;
//  private AdView adView;

  static
  {
    System.loadLibrary("cocos2dcpp");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
//    this.adView = new AdView(this, AdSize.BANNER, "ca-app-pub-1833641326787125/4199757297");
//    paramBundle = new FrameLayout.LayoutParams(-2, -2);
//    paramBundle.gravity = 81;
//    addContentView(this.adView, paramBundle);
//    this.adView.loadAd(new AdRequest());
    me = this;
  }

  public Cocos2dxGLSurfaceView onCreateView()
  {
    Cocos2dxGLSurfaceView localCocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
    localCocos2dxGLSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 8);
    return localCocos2dxGLSurfaceView;
  }

  protected void onDestroy()
  {
//    if (this.adView != null) {
//      this.adView.destroy();
//    }
    super.onDestroy();
  }
}