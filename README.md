# PathDemo
本工程包含两个自定义控件，一个是使用贝赛尔曲线画圆，另一个是画一个太极的图片。效果图如下：
![](https://github.com/yizhanzjz/ImageRepo/raw/master/pathDemo.png)

本工程中使用贝赛尔曲线画圆有两个关键点：

第一，确定控制点；第二，画其他3段圆弧时使用画布旋转可以复用画第一段圆弧的代码

对于第一点，主要依靠查资料得到下面这张图
![](https://github.com/yizhanzjz/ImageRepo/raw/master/controlDot.png)

其他就没什么好说的了，参考源码吧