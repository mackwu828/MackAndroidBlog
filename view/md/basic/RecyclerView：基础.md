
## 一、初始化 RecyclerView
```
/**
 * init vertical recycler view
 */
fun Activity.initVerticalRecyclerView(adapter: RecyclerView.Adapter<*>) {
    // 布局管理器。线性布局，竖直方向
    recycler_view.layoutManager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
    // ？
    recycler_view.setHasFixedSize(true)
    // 设置adapter
    recycler_view.adapter = adapter
}
```


## 二、初始化 adapter
```
        val list = listOf("Java", "Kotlin", "Android")
        val adapter = RecyclerAdapter(list)
```

## 三、adapter
```
class RecyclerAdapter(val list: List<String>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tvText.text = list[position]
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tv_test)
    }
}
```