#

##
```
class TextTabViewHolder(view: View) : TabViewHolder<TextTab>(view), LayoutContainer {

    override val containerView: View = view

    override fun onBindGroupHolder(position: Int, viewType: Int, data: TextTab) {
        text_view.text = data.data
    }

}
```
