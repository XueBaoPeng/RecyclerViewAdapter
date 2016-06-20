package ws.dyt.library.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import java.util.List;

import ws.dyt.library.adapter.base.BaseAdapter;
import ws.dyt.library.viewholder.BaseViewHolder;

/**
 * Created by yangxiaowei on 16/6/8.
 *
 * 支持一种item布局和多种item布局
 * 1. 采用{@link #MultiAdapter(Context, List, int)} 构造方法时，最后一个参数表示单一item的布局
 * 2. 采用{@link #MultiAdapter(Context, List)} 构造方法时需要重新 {@link #getItemViewLayout(int)} 设置每一项布局
 */
abstract
public class MultiAdapter<T> extends BaseAdapter<T> implements MultiItemViewType {
    private @LayoutRes int itemLayoutResId;

    /**
     * 调用该构造方法时需要调用 {@link #getItemViewLayout(int)} 设置item布局
     * @param context
     * @param datas
     */
    public MultiAdapter(Context context, List<T> datas) {
        super(context, datas);
    }

    /**
     * 调用该构造方法时默认数据项都采用 itemLayoutResId 布局，同样可以调用 {@link #getItemViewLayout(int)} 重新设置item布局
     * @param context
     * @param datas
     * @param itemLayoutResId
     */
    public MultiAdapter(Context context, List<T> datas, @LayoutRes int itemLayoutResId) {
        this(context, datas);
        this.itemLayoutResId = itemLayoutResId;
    }

    @Override
    final
    public int convertDataSectionItemViewType(int position) {
        if (isEmpty()) {
            return super.convertDataSectionItemViewType(position);
        }
        return getItemViewLayout(position);
    }

    @Override
    final
    public BaseViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(inflater.inflate(viewType, parent, false));
    }

    @Override
    public int getItemViewLayout(int position) {
        return this.itemLayoutResId;
    }
}