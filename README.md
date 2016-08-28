# RecyclerItemDecoration

recyclerView使用的一些辅助类

比如实现九宫格呢或者全部展开的List等：关键类LinearItemDecorationration、GridLayoutItemDecoration、FullyGridLayoutManager、FullLinearlayoutManager

<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/1.gif" width=300/> 
<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/2.gif" width=300/>
<img src="https://github.com/happylishang/RecyclerItemDecoration/blob/master/img/3.gif" width=300/>

#使用全展开的recyclerview时注意，不要获取焦点，防止自动滚动
  
      mRecyclerView.setFocusable(false);
