package com.charon.www.younghawkdemo.view;

/**
 * 项目名称：YoungHawkDemo
 * 类描述：
 * 创建人：Charon
 * 创建时间：2017/7/27 15:21
 * 修改人：Charon
 * 修改时间：2017/7/27 15:21
 * 修改备注：
 */

public  interface IBaseItemView  {
    void loading(boolean loading);

    void showError();

    void editItem(int position);

    void deleteItem(int position);

    void refresh(boolean refresh);
}
