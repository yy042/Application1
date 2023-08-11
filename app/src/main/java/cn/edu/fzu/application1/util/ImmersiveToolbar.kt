package cn.edu.fzu.application1.util

// 导入必要的库
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import cn.edu.fzu.application1.R
import cn.edu.fzu.application1.databinding.IconTextviewBinding
import cn.edu.fzu.application1.databinding.ImmersiveToolbarBinding
import com.gyf.immersionbar.ImmersionBar

// 定义一个自定义标题栏View类，继承自Toolbar
class ImmersiveToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    // 定义一个返回按钮的点击事件监听器接口
    interface OnBackClickListener {
        fun onBackClick()
    }

    // 定义一个返回按钮的点击事件监听器变量
    private var onBackClickListener: OnBackClickListener? = null

    // 定义一个标题文本的变量
    private var titleText: String? = null
    // 定义一个状态栏颜色的变量
    private var statusBarColor: Int = R.color.white
    // 定义一个状态栏字体颜色的变量
    private var statusBarDarkFont: Boolean = true

    private var verticalPadding: Int = 0
    private var horizontalPadding: Int = 0

    // 定义一个ViewBinding的变量
    private var binding: ImmersiveToolbarBinding

    // 初始化代码块，在创建对象时执行
    init {
        // 从布局文件中加载视图，并绑定到ViewBinding变量中
        binding = ImmersiveToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        // 从属性集中获取自定义属性的值，如果有的话
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImmersiveToolbar)
        titleText = typedArray.getString(R.styleable.ImmersiveToolbar_titleText)
        statusBarColor = typedArray.getResourceId(R.styleable.ImmersiveToolbar_statusBarColor, R.color.white)
        statusBarDarkFont = typedArray.getBoolean(R.styleable.ImmersiveToolbar_statusBarDarkFont, true)
        verticalPadding = typedArray.getDimensionPixelSize(R.styleable.ImmersiveToolbar_verticalPadding,10)
        horizontalPadding = typedArray.getDimensionPixelSize(R.styleable.ImmersiveToolbar_horizontalPadding,10)

        typedArray.recycle()

        // 设置标题文本视图的文本为自定义属性的值，如果有的话
        binding.tvTitle?.text = titleText ?: "标题"
        // 设置返回按钮的点击事件监听器，调用接口中的方法
        binding.ivBack?.setOnClickListener {
            onBackClickListener?.onBackClick()
        }

        // 使用ImmersionBar库处理沉浸式，设置状态栏和标题栏颜色一致，以及其他参数
        setBarColor(statusBarColor,statusBarDarkFont)

        //设置padding
        this.setPaddingRelative(horizontalPadding,verticalPadding,horizontalPadding,verticalPadding)

    }

    fun setBarColor(color: Int=R.color.white,isDarkFont:Boolean=true){
        val colorValue = getResources().getColor(color) // 转换颜色资源id为颜色值
        binding.root.setBackgroundColor(colorValue)
        //处理沉浸式
        ImmersionBar.with(context as Activity)
            .statusBarColor(color) // 状态栏颜色为自定义属性的值，如果有的话
            .fitsSystemWindows(true) // 适配刘海屏或者水滴屏等异形屏幕
            .statusBarDarkFont(isDarkFont) // 状态栏字体颜色为自定义属性的值，如果有的话
            .titleBar(this)
            .init()
    }

    // 定义一个设置返回按钮点击事件监听器的方法，供外部调用
    fun setOnBackClickListener(listener: OnBackClickListener) {
        onBackClickListener = listener
    }

    // 定义一个设置标题文本的方法，供外部调用
    fun setTitleText(text: String) {
        titleText = text
        binding.tvTitle?.text = titleText
    }

    // 定义一个设置水平方向上的padding的方法，供外部调用
    fun setHorizontalPadding(padding: Int) {
        horizontalPadding = padding
        requestLayout() // 请求重新布局
    }

    // 定义一个获取水平方向上的padding的方法，供外部调用
    fun getHorizontalPadding(): Int {
        return horizontalPadding
    }

    // 定义一个设置垂直方向上的padding的方法，供外部调用
    fun setVerticalPadding(padding: Int) {
        verticalPadding = padding
        requestLayout() // 请求重新布局
    }

    // 定义一个获取垂直方向上的padding的方法，供外部调用
    fun getVerticalPadding(): Int {
        return verticalPadding
    }

}

