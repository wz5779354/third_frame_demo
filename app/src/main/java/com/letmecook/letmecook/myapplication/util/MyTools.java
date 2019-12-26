package com.letmecook.letmecook.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.EditText;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by gaoshuqing on 2017/1/3.
 */

public class MyTools {


    private static String TruncateUrlPage(String strURL){
        String strAllParam=null;
        String[] arrSplit=null;
        strURL=strURL.trim();
        arrSplit=strURL.split("[?]");
        if(strURL.length()>1){
            if(arrSplit.length>1){
                for (int i=1;i<arrSplit.length;i++){
                    strAllParam = arrSplit[i];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 从url里截取参数
     */
    public static HashMap<String, String> urlSplit(String data){
        HashMap<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit=null;
        String strUrlParam=TruncateUrlPage(data);
        if(strUrlParam==null){
            return mapRequest;
        }
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit){
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");
            //解析出键值
            if(arrSplitEqual.length>1){
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            }else{
                if(arrSplitEqual[0]!=""){
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    /**
     * 都是需要嵌套nestscrollview
     */
//    public static void initRecycleView(Context context, RecyclerView recyclerView, int ori) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, ori, false);
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setNestedScrollingEnabled(false);
//    }
//
//    public static void initRecycleView(Context context, RecyclerView recyclerView) {
//        initRecycleView(context, recyclerView, LinearLayoutManager.VERTICAL);
//    }
//
//    public static void initGridRecycleView(Context context, RecyclerView recyclerView, int count) {
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, count);
//        gridLayoutManager.setSmoothScrollbarEnabled(true);
//        gridLayoutManager.setAutoMeasureEnabled(true);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setNestedScrollingEnabled(false);
//    }

    /**
     * @function 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "[1]\\d{10}";// "[1]"代表第1位为数字1，"[34578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 初始化webview
     */
    public static void initWebSetting(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }




    /**
     * 获取缓存的地址
     *
     * @return
     */
    public static File getCacheDir() {
        File cacheDir = new File(Environment.getExternalStorageDirectory().getPath() +
                File.separator + "DongYa");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static File getPhotoCacheDir() {
        File cacheDir = new File(Environment.getExternalStorageDirectory().getPath() +
                File.separator + "DongYa" + File.separator + "Photo");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 获取屏幕的宽高
     *
     * @param window
     */
    public static Integer[] getWindowWH(Window window) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        Integer[] p = {outMetrics.widthPixels, outMetrics.heightPixels};
        return p;
    }

    /**
     * 获取手机大小（分辨率）
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getScreenPix(Activity activity) {
        // DisplayMetrics 一个描述普通显示信息的结构，例如显示大小、密度、字体尺寸
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        // 获取手机窗口的Display 来初始化DisplayMetrics 对象
        // getManager()获取显示定制窗口的管理器。
        // 获取默认显示Display对象
        // 通过Display 对象的数据来初始化一个DisplayMetrics 对象
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displaysMetrics);
        return displaysMetrics;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     */
    public static String addComma(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        // 将[789,456,] 中最后一个[,]去除
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        // 将数字重新反转
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;
    }

    /**
     * 特殊字体
     */
    public static void tvDidotType(Context context, TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Didot.ttf");
        textView.setTypeface(face);
    }

    /**
     * 给textview设置中划线
     */
    public static void setTvMiddleStrike(TextView textView) {
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
    }




    /**
     * 精确到小数点后两位
     */
    public static String decimalFormat(String input) {
        DecimalFormat df = new DecimalFormat("#.##");
        double d = Double.parseDouble(input);
        String st = df.format(d);
        return st;
    }

    public static String decimalFormat(double input) {
        DecimalFormat df = new DecimalFormat("#.##");
        double d = input;
        String st = df.format(d);
        return st;
    }

    public static void inputMoney(EditText edtNumber, CharSequence charSequence, String max) {
        if (charSequence.toString().contains(".")) {
            if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > 2) {
                charSequence = charSequence.toString().subSequence(0,
                        charSequence.toString().indexOf(".") + 3);
                edtNumber.setText(charSequence);
                edtNumber.setSelection(charSequence.length());
            }
        }
        if (charSequence.toString().trim().substring(0).equals(".")) {
            charSequence = "0" + charSequence;
            edtNumber.setText(charSequence);
            edtNumber.setSelection(2);
        }
        if (charSequence.toString().startsWith("0")
                && charSequence.toString().trim().length() > 1) {
            if (!charSequence.toString().substring(1, 2).equals(".")) {
                edtNumber.setText(charSequence.subSequence(0, 1));
                edtNumber.setSelection(1);
                return;
            }
        }
        if (!TextUtils.isEmpty(max)) {
            if (Double.parseDouble(charSequence.toString()) > Double.parseDouble(max)) {
                edtNumber.setText(max);
                edtNumber.setSelection(max.length());
            }
        }
    }

    /**
     * 获取指定文件大小
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
            Logger.e("获取文件大小", "文件不存在!");
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 获取指定文件夹
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 截取字符串
     */
    public static String subStr(String content, String start, String end) {
        String resultStr = "";
        if (TextUtils.isEmpty(end)) {
            resultStr = content.substring(content.indexOf(start) + start.length());
        } else {
            resultStr = content.substring(content.indexOf(start) + start.length(), content.indexOf(end));
        }
        return resultStr;
    }


    /**
     * 获取不重复的随机数
     */
    public static String getRandomStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 压缩图片
     *
     * @param path
     * @return
     */
    public static File scal(String path) {
        // String path = fileUri.getPath();
        File outputFile = new File(path);
        long fileSize = outputFile.length();
        Log.i("ao", "filesize=" + fileSize);
        final long fileMaxSize = 200 * 1024;
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int height = options.outHeight;
        int width = options.outWidth;
        if (fileSize >= fileMaxSize) {
            double scale = Math.sqrt((float) fileSize / fileMaxSize);
            options.outHeight = (int) (height / scale);
            options.outWidth = (int) (width / scale);
            options.inSampleSize = (int) (scale + 0.5);
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(path, options);

            String orientation = "" + getExifOrientation(path);// 获取旋转角度
            int angle = 0;
            if (orientation != null && !"".equals(orientation)) {
                angle = Integer.parseInt(orientation);
            }
            if (angle != 0) {
                // 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
                Matrix m = new Matrix();
                m.setRotate(angle); // 旋转angle度
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);// 重新生成图片
            }

            outputFile = new File(MyTools.createImageFile().getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.i("ds", "***" + outputFile.length());
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(MyTools.createImageFile().getPath());
                MyTools.copyFileUsingFileChannels(tempFile, outputFile);
            }
        }else {
            outputFile = new File(path);
            FileOutputStream fos = null;
            try {
                bitmap = BitmapFactory.decodeFile(path);
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.i("ds", "***" + outputFile.length());
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            } else {
                File tempFile = outputFile;
                outputFile = new File(MyTools.createImageFile().getPath());
                MyTools.copyFileUsingFileChannels(tempFile, outputFile);
            }
        }

        return outputFile;
    }

    /**
     * 获取图片的旋转角度
     *
     * @param filepath
     * @return
     */
    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    public static Uri createImageFile() {
        // Create an image file name
        String imageFileName = UUID.randomUUID().toString();
        File storageDir = getGalleryCacheDir();
        File image = null;
        try {
            image = File.createTempFile(imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        return Uri.fromFile(image);
    }

    /**
     * 获取缓存的地址
     *
     * @return
     */
    public static File getGalleryCacheDir() {
        File cacheDir = new File(Environment.getExternalStorageDirectory().getPath() +
                File.separator + "baijiakuke" + File.separator + "Gallery");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }

    public static void copyFileUsingFileChannels(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }
    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String pkName = packageInfos.get(i).packageName;
                if (pkName.equals(packageName)) return true;
            }
        }
        return false;
    }

    /**
     * raw请求
     * @param jsonStr
     * @return
     */
    public static RequestBody getBody(String jsonStr) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        return requestBody;
    }

    /**
     * 添加参数
     * @param jsonObject
     * @param key
     * @param value
     */
    public static void putJsonValue(JSONObject jsonObject, String key, Object value) {
        try {
            if (value != null) {
                jsonObject.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 检测字符串是否为空或无内容
     *
     * @param srcString
     * @return
     */
    public static boolean isNull(String srcString) {
        if (srcString != null && !srcString.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 传入edittext控件和需要小数点后保留的位数
     * 使输入框显示
     *
     * @param edt 需要控制的输入框
     * @param num 小数点后保留位数
     */
    public static void formatNumberStr(final EditText edt, final int num) {

        String temp = "############0";
        if (num > 0) {
            temp = temp + ".";
            for (int i = 0; i < num; i++) {
                temp = temp + "#";
            }
        }
        //控制输入内容必须为数字或者小数点格式 防止空格等
        edt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);


        final String finalTemp = temp;
        edt.addTextChangedListener(
                new TextWatcher() {


                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

//                        if (RegexpUtils.regexEdttext(edt.getContext(),false, edt)) {
//                            return;
//                        }
                        String text = edt.getText().toString().trim();

                        //首个字符是0后面不是。 去掉0
                        //首个字符是。 前面加0
                        //小数点后最多两位
                        if (TextUtils.isEmpty(text))
                            return;
                        if (text.startsWith("0") && text.length() == 2 && !TextUtils.equals("0.", text)) {
                            text = text.replace("0", "") + "";
                            edt.setText(text);
                            edt.setSelection(text.length());
                            return;
                        }
                        if (!text.contains(".")) {
                            return;
                        }

                        if (text.startsWith(".")) {
                            text = "0" + text;
                            edt.setText(text);
                            edt.setSelection(text.length());
                            return;
                        }
                        if (!text.endsWith(".")) {
                            if (text.split("\\.")[1].length() <= num) {
                                return;
                            }

                            DecimalFormat fnum = new DecimalFormat(finalTemp);
                            fnum.setRoundingMode(RoundingMode.DOWN);

                            text = fnum.format(Double.parseDouble(text));
                            edt.setText(text);
                            edt.setSelection(text.length());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );
    }


    public static void showKeybord(View v, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm !=null){
            v.requestFocus();
            imm.showSoftInput(v, 0);
        }


    }
    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm !=null){
            imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }

    }

    public static void closeKeybord_1(View v, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm !=null){
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高px
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

   /* public static String getWeek(int time,Context mContext) {

        String weekDayStr = "";
        switch (time) {
            case 1:
                weekDayStr = mContext.getResources().getString(R.string.Monday);
                break;
            case 2:
                weekDayStr = mContext.getResources().getString(R.string.Tuesday);
                break;
            case 3:
                weekDayStr = mContext.getResources().getString(R.string.Wednesday);
                break;
            case 4:
                weekDayStr = mContext.getResources().getString(R.string.Thursday);
                break;
            case 5:
                weekDayStr = mContext.getResources().getString(R.string.Friday);
                break;
            case 6:
                weekDayStr = mContext.getResources().getString(R.string.Saturday);
                break;
            case 7:
                weekDayStr = mContext.getResources().getString(R.string.Sunday);
                break;
            default:
        }
        return weekDayStr;
    }*/

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        if (null == context) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    public static String replaceString(String str,String str1) {
        if (str != null) {
//            int indexStart = str.indexOf("/public");
            int indexStart = str.indexOf(str1);
            str = str.substring(indexStart + 1, str.length());

            //  如果字符串中有"abc"则替换成"ABC"
//        str=str.replace("https://s3.us-east-2.amazonaws.com/letmecook-userfiles-mobilehub-2074145796/","");
            return str;
        }
        return "";

    }

    public static String getSubStringFromEnd(String str,String str1) {
        if (str != null) {
            int indexStart = str.lastIndexOf(str1);
            str = str.substring(indexStart + 1, str.length());

            //  如果字符串中有"abc"则替换成"ABC"
//        str=str.replace("https://s3.us-east-2.amazonaws.com/letmecook-userfiles-mobilehub-2074145796/","");
            return str;
        }
        return "";

    }

    /**
     * 获取字符数量 汉字占2个，英文占一个
     *
     * @param text
     * @return
     */
    public static int getTextLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) > 255) {
                length += 2;
            } else {
                length++;
            }
        }
        return length;
    }

    /**
     * 获取字符串中字符长度的字符（一个汉字占2个字符，英文字母和数字占一个字符）
     * @param text 字符串
     * @param totalLength 需要截取的字符串的长度
     * @return  返回截取固定长度的字符
     */
    public static String getSubText(String text,int totalLength) {
        int length = 0;
        String str ="";
        char[] chars = text.toCharArray();
        int charLength = chars.length;
        for (int i = 0; i < charLength; i++) {
            if (chars[i] > 255) {
                length += 2;
            } else {
                length++;
            }
            if(length ==totalLength){
                str = text.substring(0,i+1);
                return str;
            }
            if(length>totalLength){
                str= text.substring(0,i);
                return str;
            }
        }
        return "";
    }


    /**
     * 截取  @xxx: 格式的字符串
     * @param str
     */
    public static String removeTheLastColon (String str){
        if(str.contains("：")){
        return str.substring(0,str.length()-1);
        }
        return str;
    }


}
