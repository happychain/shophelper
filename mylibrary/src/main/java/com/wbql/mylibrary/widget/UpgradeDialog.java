package com.wbql.mylibrary.widget;

import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.wbql.mylibrary.R;

public class UpgradeDialog extends Dialog {
    public UpgradeDialog(@NonNull Context context) {
        super(context);
    }

    public static class Builder {
        private View mLayout;
        private TextView mTitle;
        private TextView mMessage;
        private Button mPositiveButton, mNegativeButton;
        private onPositiveButtonClickListener mPositiveButtonClickListener;
        private onNegativeButtonClickListener mNegativeButtonClickListener;
        private UpgradeDialog mDialog;

        public Builder(Context context) {
            mDialog = new UpgradeDialog(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.tip_upgrade_dialog_layout, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
//            mTitle = mLayout.findViewById(R.id.dialog_title);
//            mMessage = mLayout.findViewById(R.id.dialog_message);
//            mPositiveButton = mLayout.findViewById(R.id.btn_ensure);
//            mNegativeButton = mLayout.findViewById(R.id.btn_cancel);
        }

        /**
         * 设置 Dialog 标题
         */
        public UpgradeDialog.Builder setTitle(@NonNull String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }

        /**
         * 设置 Message
         */
        public UpgradeDialog.Builder setMessage(@NonNull String message) {
            mMessage.setText(message);
            return this;
        }

        /**
         * 设置按钮文字和监听
         */
        public UpgradeDialog.Builder setPositiveButton(@NonNull String text, onPositiveButtonClickListener listener) {
            mPositiveButton.setText(text);
            mPositiveButtonClickListener = listener;
            return this;
        }

        public UpgradeDialog.Builder setNegativeButton(@NonNull String text, onNegativeButtonClickListener listener) {
            mNegativeButton.setText(text);
            mNegativeButtonClickListener = listener;
            return this;
        }

        public UpgradeDialog create() {
            initEvent();
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            return mDialog;
        }

//        public interface onPositiveButtonClickListener {
//            void onPositiveClick();
//        }
//
//        public interface onNegativeButtonClickListener {
//            void onNegativeOnclick();
//        }

        private void initEvent() {
            //设置确定按钮被点击后，向外界提供监听
            mPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     if (mPositiveButtonClickListener != null) {
                         mPositiveButtonClickListener.onPositiveClick();
                     }
                }
            });
            //设置取消按钮被点击后，向外界提供监听
            mNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNegativeButtonClickListener != null) {
                        mNegativeButtonClickListener.onNegativeOnclick();
                    }
                }
            });
        }
    }

    public interface onPositiveButtonClickListener {
        void onPositiveClick();
    }

    public interface onNegativeButtonClickListener {
        void onNegativeOnclick();
    }
}
