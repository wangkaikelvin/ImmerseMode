package com.yunxian.immerse;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.yunxian.immerse.enumeration.ImmerseType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 沉浸配置参数
 *
 * @author AShuai
 * @email ls1110924@gmail.com
 * @date 17/2/3 上午9:01
 */
public final class ImmerseConfiguration {

    final ImmerseType mImmerseTypeInKK;
    final ImmerseType mImmerseTypeInL;

    private ImmerseConfiguration(@NonNull ImmerseType immerseTypeInKK, @NonNull ImmerseType immerseTypeInL) {
        this.mImmerseTypeInKK = immerseTypeInKK;
        this.mImmerseTypeInL = immerseTypeInL;
    }

    public static final int NORMAL = 1;
    public static final int TRANSLUCENT = 2;
    public static final int TRANSPARENT = 3;

    public static class Builder {

        @IntDef({NORMAL, TRANSLUCENT})
        @Retention(RetentionPolicy.SOURCE)
        private @interface ImmerseConfigType4KK {
        }

        @IntDef({NORMAL, TRANSLUCENT, TRANSPARENT})
        @Retention(RetentionPolicy.SOURCE)
        private @interface ImmerseConfigType4L {
        }

        private int mStatusBarModeInKK = NORMAL;
        private int mNavigationBarModeInKK = NORMAL;
        private boolean mFullScreenInKK = false;
        private boolean mAdjustResizeInKK = false;

        private int mStatusBarModeInL = NORMAL;
        private int mNavigationBarModeInL = NORMAL;
        private boolean mFullScreenInL = false;
        private boolean mAdjustResizeInL = false;

        public Builder() {
        }

        public Builder setStatusBarModeInKK(@ImmerseConfigType4KK int statusBarModeInKK) {
            this.mStatusBarModeInKK = statusBarModeInKK;
            return this;
        }

        public Builder setNavigationBarModeInKK(@ImmerseConfigType4KK int navigationBarModeInKK) {
            this.mNavigationBarModeInKK = navigationBarModeInKK;
            return this;
        }

        public Builder setFullScreenInKK(boolean fullScreenInKK) {
            this.mFullScreenInKK = fullScreenInKK;
            return this;
        }

        public Builder setAdjustResizeInKK(boolean adjustResizeInKK) {
            this.mAdjustResizeInKK = adjustResizeInKK;
            return this;
        }

        public Builder setStatusBarModeInL(@ImmerseConfigType4L int statusBarModeInL) {
            this.mStatusBarModeInL = statusBarModeInL;
            return this;
        }

        public Builder setNavigationBarModeInL(@ImmerseConfigType4L int navigationBarModeInL) {
            this.mNavigationBarModeInL = navigationBarModeInL;
            return this;
        }

        public Builder setFullScreenInL(boolean fullScreenInL) {
            this.mFullScreenInL = fullScreenInL;
            return this;
        }

        public Builder setAdjustResizeInL(boolean adjustResizeInL) {
            this.mAdjustResizeInL = adjustResizeInL;
            return this;
        }

        public ImmerseConfiguration build() {
            ImmerseType immerseTypeInKK;
            ImmerseType immerseTypeInL;

            if (mStatusBarModeInKK == TRANSLUCENT && mFullScreenInKK && mAdjustResizeInKK) {
                immerseTypeInKK = ImmerseType.TLSB_NNB_FC_AR;
            } else if (mStatusBarModeInKK == TRANSLUCENT && mNavigationBarModeInKK == TRANSLUCENT) {
                immerseTypeInKK = mFullScreenInKK ? ImmerseType.TLSB_TLNB_FC : ImmerseType.TLSB_TLNB;
            } else if (mStatusBarModeInKK == TRANSLUCENT) {
                immerseTypeInKK = mFullScreenInKK ? ImmerseType.TLSB_NNB_FC : ImmerseType.TLSB_NNB;
            } else {
                immerseTypeInKK = ImmerseType.NSB_NNB;
            }

            if (mStatusBarModeInL == TRANSLUCENT && mFullScreenInL && mAdjustResizeInL) {
                immerseTypeInL = ImmerseType.TLSB_NNB_FC_AR;
            } else if (mStatusBarModeInL == TRANSPARENT && mFullScreenInL && mAdjustResizeInL) {
                immerseTypeInL = ImmerseType.TPSB_NNB_FC_AR;
            } else if (mStatusBarModeInL == TRANSPARENT && mNavigationBarModeInL == TRANSLUCENT) {
                immerseTypeInL = mFullScreenInL ? ImmerseType.TPSB_TLNB_FC : ImmerseType.TPSB_TLNB;
            } else if (mStatusBarModeInL == TRANSPARENT) {
                immerseTypeInL = mFullScreenInL ? ImmerseType.TPSB_NNB_FC : ImmerseType.TPSB_NNB;
            } else if (mStatusBarModeInL == TRANSLUCENT && mNavigationBarModeInL == TRANSLUCENT) {
                immerseTypeInL = mFullScreenInL ? ImmerseType.TLSB_TLNB_FC : ImmerseType.TLSB_TLNB;
            } else if (mStatusBarModeInL == TRANSLUCENT) {
                immerseTypeInL = mFullScreenInL ? ImmerseType.TLSB_NNB_FC : ImmerseType.TLSB_NNB;
            } else {
                immerseTypeInL = ImmerseType.NSB_NNB;
            }

            return new ImmerseConfiguration(immerseTypeInKK, immerseTypeInL);
        }

    }

}
