package net.daverix.habanero.page;


import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.daverix.habanero.R;
import net.daverix.habanero.databinding.WidgetItemTitleBinding;

import javax.inject.Inject;

public class TitleWidgetViewHolderFactory implements ViewHolderFactory {
    private final LayoutInflater inflater;

    @Inject
    public TitleWidgetViewHolderFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public ViewHolder create(ViewGroup group) {
        return new TitleViewHolder(DataBindingUtil.inflate(inflater,
                R.layout.widget_item_title,
                group,
                false));
    }

    private class TitleViewHolder extends ViewHolder {
        private final WidgetItemTitleBinding binding;

        public TitleViewHolder(WidgetItemTitleBinding binding) {
            super(binding);
            this.binding = binding;
        }

        @Override
        void bindWidget(WidgetViewModel viewModel) {
            if (!(viewModel instanceof TitleWidgetItemViewModel))
                throw new IllegalArgumentException("viewModel not instance of TitleWidgetItemViewModel");

            binding.setWidget((TitleWidgetItemViewModel) viewModel);
        }
    }
}
