package cn.edu.seu.alumni.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BasisAdapter<T, K> extends BaseAdapter {

    private List<T> entities;
    private Context context;
    private Class<K> classType;

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public void addEntities(List<T> entities){
        this.entities.addAll(entities);
    }

    public BasisAdapter(Context context, List<T> entities, Class<K> classType) {
        this.context = context;
        this.entities = entities;
        this.classType = classType;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public T getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        K holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
            try {
                holder = classType.newInstance();
                Log.i("INFO", "Complete");
            } catch (InstantiationException e) {
                Log.i("INFO", "InstantiationException");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.i("INFO", "IllegalAccessException");
                e.printStackTrace();
            }
            initViewHolder(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (K) convertView.getTag();
        }
        T entity = entities.get(position);
        setDataIntoView(holder, entity);
        return convertView;
    }

    protected abstract void setDataIntoView(K holder, T entity);

    protected abstract void initViewHolder(View convertView, K holder);

    public abstract int getItemLayout();

    public void removeItem(int position) {
        entities.remove(position);
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public List<T> getEntities(){
        return  entities;
    }
}
