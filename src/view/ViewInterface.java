package view;

import java.util.List;

public interface ViewInterface<T>
{
    public void setPresenter(T presenter);

    public void setListDatas(List<T> listD);

    public void affMsg(String msg);
}
