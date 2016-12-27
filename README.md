# RecyclerItemDecoration

recyclerView使用的一些辅助类

比如实现九宫格呢或者全部展开的List等：关键类LinearItemDecorationration、GridLayoutItemDecoration、FullyGridLayoutManager、FullLinearlayoutManager

<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/1.gif" width=300/> 
<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/2.gif" width=300/>
<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/3.gif" width=300/>
<img src="http://upload-images.jianshu.io/upload_images/1460468-71486651b73319b4.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1080/q/50" width=300/>   

#使用全展开的recyclerview时注意，不要获取焦点，防止自动滚动
  
      mRecyclerView.setFocusable(false);
      mRecyclerView.setNestedScrollingEnabled(false);（根据需求灵活使用）
