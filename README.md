# RecyclerItemDecoration

recyclerView使用的一些辅助类

比如实现九宫格呢或者全部展开的List等：关键类LinearItemDecorationration、GridLayoutItemDecoration、FullyGridLayoutManager、FullLinearlayoutManager      
<img src="http://upload-images.jianshu.io/upload_images/1460468-3b6740b773221600.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50" width=290 />
<img src="http://upload-images.jianshu.io/upload_images/1460468-b76641faeff60bec.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50" width=290 /> 
<img src="http://upload-images.jianshu.io/upload_images/1460468-02a8516143c15d36.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50" width=290 />
<img src="http://upload-images.jianshu.io/upload_images/1460468-a58912bf7f3c1f34.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50" width=290 />   

#使用全展开的recyclerview时注意，不要获取焦点，防止自动滚动
  
      mRecyclerView.setFocusable(false);
      mRecyclerView.setNestedScrollingEnabled(false);（根据需求灵活使用）
