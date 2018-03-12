package com.ivzb.semaphore._base.ui;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivzb.semaphore._base.ui._contracts.BaseAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultAdapter<T>
        extends RecyclerView.Adapter<DefaultAdapter.ViewHolder>
        implements BaseAdapter<T> {

    protected Context mContext;
    protected List<T> mEntities;

    public DefaultAdapter(Context context) {
        super();
        mContext = context;
        mEntities = new ArrayList<>();
    }

    @Override
    public void prepend(List<T> entities) {
        if (entities == null || entities.size() == 0) return;

        mEntities.addAll(0, entities);
        notifyItemRangeInserted(0, entities.size());
//        notifyDataSetChanged();
    }

    @Override
    public void append(List<T> entities) {
        if (entities == null || entities.size() == 0) return;

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
    public int size() {
        return mEntities.size();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return Parcels.wrap(mEntities);
    }

    @Override
    public void onRestoreInstanceState(Parcelable parcelable) {
        List<T> entities = Parcels.unwrap(parcelable);
        append(entities);
    }

    @Override
    public int getItemCount() {
        return mEntities.size();
    }

    public class ViewHolder<T extends View> extends RecyclerView.ViewHolder {

        private T mBinding;

        public ViewHolder(T binding) {
            super(binding);
            mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }
}