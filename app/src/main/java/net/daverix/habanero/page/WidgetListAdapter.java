/*
    Standalone Android Client for OpenHAB server
    Copyright (C) 2016  David Laurell

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package net.daverix.habanero.page;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class WidgetListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<WidgetViewModel> items = new ArrayList<>();
    private final Map<Integer, ViewHolderFactory> factoryMap;
    private final ViewHolderFactory defaultFactory;

    @Inject
    public WidgetListAdapter(Map<Integer,ViewHolderFactory> factoryMap, ViewHolderFactory defaultFactory) {
        this.factoryMap = factoryMap;
        this.defaultFactory = defaultFactory;
    }

    public void addItem(WidgetViewModel x) {
        synchronized (items) {
            items.add(x);
        }
        notifyItemInserted(items.indexOf(x));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderFactory viewHolderFactory = factoryMap.get(viewType);
        if(viewHolderFactory == null)
            viewHolderFactory = defaultFactory;

        return viewHolderFactory.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindWidget(items.get(position));
        holder.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
