package com.ivzb.encrypted_chat._base.ui.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivzb.encrypted_chat._base.ui._contracts.adapters.BaseAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultAdapter<T>
        extends RecyclerView.Adapter<DefaultAdapter.ViewHolder>
        implements BaseAdapter<T> {

    protected Context mContext;
    protected List<T> mEntities;

    public DefaultAdapter(Context context) {
        mContext = context;
        mEntities = new ArrayList<>();
    }

    @Override
    public void add(List<T> entities) {
        if (entities == null) return;

        int start = getItemCount();
        mEntities.addAll(entities);
        notifyItemRangeInserted(start, entities.size());
    }

    @Override
    public void clear() {
        mEntities = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return Parcels.wrap(mEntities);
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        List<T> entities = Parcels.unwrap(parcelable);
        add(entities);
    }

    @Override
    public int getItemCount() {
        return mEntities.size();
    }

    public class ViewHolder<T extends View> extends RecyclerView.ViewHolder {

        private T mBinding;

        public ViewHolder(T binding) {
            // todo: inspect this later
            //super(binding.getRoot());
            super(binding);
            mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }
}