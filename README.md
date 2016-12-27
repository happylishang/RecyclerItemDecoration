# RecyclerItemDecoration

recyclerView使用的一些辅助类

比如实现九宫格呢或者全部展开的List等：关键类LinearItemDecorationration、GridLayoutItemDecoration、FullyGridLayoutManager、FullLinearlayoutManager      
<img src="https://github.com/happylishang/happylishang.github.io/blob/master/images/android/view/RecyclerItemDecoration/1.gif?raw=true" width=290 />
<img src="https://github.com/happylishang/happylishang.github.io/blob/master/images/android/view/RecyclerItemDecoration/2.gif?raw=true" width=290 /> 
<img src="https://github.com/happylishang/happylishang.github.io/blob/master/images/android/view/RecyclerItemDecoration/3.gif?raw=true" width=290 />
<img src="https://github.com/happylishang/happylishang.github.io/blob/master/images/android/view/RecyclerItemDecoration/horizon.gif?raw=true" width=290 />   

#使用全展开的recyclerview时注意，不要获取焦点，防止自动滚动
  
      mRecyclerView.setFocusable(false);
      mRecyclerView.setNestedScrollingEnabled(false);（根据需求灵活使用）
