package com.yinshan.livedatademo.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yinshan.livedatademo.R;

/**
 * @author limh
 * @function
 * @date 2018/11/26 17:02
 */
public class LoadingDialog extends Dialog {

    private static final String TAG = "LoadingDialog";
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_SUCCESS = 2;

    public LoadingDialog(Context context) {
        super(context, R.style.styleDialog);
    }

    public static class Builder {
        private Context context;
        private String msg;
        private boolean isOutTouchCancel = false;
        private boolean isBgDark = false;
        private OnClickListener onDismissListener;
        private int type = 1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setOutTouchCancel(boolean outTouchCancel) {
            isOutTouchCancel = outTouchCancel;
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public Builder setOnDismissListener(OnClickListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public Builder setBgDark(boolean bgDark) {
            this.isBgDark = bgDark;
            return this;
        }

        public LoadingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (null == inflater) {
                Log.e(TAG, "dialog create is fail,inflater is null");
                return null;
            }
            View layout = inflater.inflate(R.layout.layout_dialog_loading, null);
            final LoadingDialog loadingDialog = new LoadingDialog(context);
            loadingDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            loadingDialog.setCanceledOnTouchOutside(isOutTouchCancel);
            TextView txtMsg = layout.findViewById(R.id.txt_dialog_loading_msg);
            ProgressBar progressBar = layout.findViewById(R.id.dg_pb_loading);
            ImageView imageView = layout.findViewById(R.id.dg_img_success);

            if (TYPE_LOADING == this.type) {
                progressBar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                (new Handler()).postDelayed(new Runnable() {
                    public void run() {
                        loadingDialog.dismiss();
                        if (null !=onDismissListener) {
                            onDismissListener.onClick(loadingDialog, DialogInterface.BUTTON_NEGATIVE);
                        }

                    }
                }, 1500L);
            }
            if (TextUtils.isEmpty(msg)) {
                txtMsg.setVisibility(View.GONE);
            } else {
                txtMsg.setVisibility(View.VISIBLE);
                txtMsg.setText(msg);
            }
            Window window = loadingDialog.getWindow();
            if (null != window) {
                window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
                window.setBackgroundDrawableResource(R.color.transparent);
                if (!this.isBgDark) {
                    window.clearFlags(2);
                }
            }

            return loadingDialog;
        }
    }
}
