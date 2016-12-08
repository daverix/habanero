package net.daverix.habanero.page;


import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public abstract class ViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public ViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    abstract void bindWidget(WidgetViewModel model);

    void executePendingBindings() {
        binding.executePendingBindings();
    }
}
