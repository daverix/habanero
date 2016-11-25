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

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.daverix.habanero.R;
import net.daverix.habanero.databinding.WidgetItemTitleBinding;

import java.util.ArrayList;
import java.util.List;

public class WidgetListAdapter extends RecyclerView.Adapter<WidgetListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<WidgetItemViewModel> items = new ArrayList<>();

    public WidgetListAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void addItem(WidgetItemViewModel x) {
        synchronized (items) {
            items.add(x);
        }
        notifyItemInserted(items.indexOf(x));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(inflater,
                R.layout.widget_item_title,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.setWidget(items.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final WidgetItemTitleBinding binding;

        public ViewHolder(WidgetItemTitleBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
