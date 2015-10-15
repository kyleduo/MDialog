# MDialog

[中文(Blog)](http://kyleduo.com/?p=611)

**MDialog** is a library of Material Design like and easy-use dialog with **more user-friendly appearance**.

- More user-friendly appearance.
- Easy to use.
- Easy to customize.
- Less code, better result.

### This is what it looks.

![Compare](https://github.com/kyleduo/MDialog/blob/master/preview/compare.jpg?raw=true "Compare")

These screenshots are from Android 4.4. You can see the comfortable style with MDialog and what it is with built-in or dialog from support library from them.

### Easy to use.

Use with Gradle.
```
dependencies {
    compile 'com.kyleduo.mdialog:library:1.0.1'
}
```

To create a MDialog is such simple that you can just change 'AlertDialog' to 'MDialog'. This is a piece of demo code.

```
new MDialog.Builder(this).setTitle("Demo").setMessage("Message of MDialog.\nThis a easy-use library.")
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.create().show();
```

You got it.

By default, MDialog set positive button's text color with `?accentColor` of current style, so your dialog can provide continuously appearance with other components in your app.

For enhancement, The `MDialog.Builder` provides more APIs for developers customizing behaviours of dialog.


- **setAutoDismiss(boolean);** // true, auto dismiss when click buttons
- **setAutoDismiss(boolean, boolean, boolean);** // same as setAutoDismiss(boolean); but set the properties for each button (pos, neg, other)
- **setTitlemaxLines(int);** // max lines of title TextView


### Easy to customize.

These APIs are for button styles.


- **setNormalButtonBackgroundRes(int);** // background resource id for negative and other button
- **setPrimaryButtonBackgroundRes(int);** // background resource id for positive button
- **setNormalButtonTextColor(int);** // text color for negative and other button
- **setPrimaryButtonTextColor(int);** // text color for positive button


MDialog provides a more useful way to customize the whole appearance of dialog. Just use constructor below and put your custom layout res id into it.

```
new MDialog.Builder(this, R.layout.layout_dialog)
```

In the layout.xml, you **NEED** to configure the ids in it with specific ids in `ids.xml` just like the default `layout_mdialog.xml` does.

This image shows what you can get with MDialog and has not much changes in code.

![Custom](https://github.com/kyleduo/MDialog/blob/master/preview/custom.jpg?raw=true "Custom")

Code for customize buttons.

```
new MDialog.Builder(this).setTitle("Custom Button").setMessage("Easily customize buttons in Dialog with background and text color.")
				.setPositiveButton("OK", null)
				.setNegativeButton("Cancel", null)
				.setNormalButtonTextColor(Color.parseColor("#666666"))
				.setPrimaryButtonTextColor(Color.parseColor("#666666"))
				.setNormalButtonBackgroundRes(R.drawable.dialog_bt_bg_neg)
				.setPrimaryButtonBackgroundRes(R.drawable.dialog_bt_bg_pos)
				.setCancelable(false)
				.create().show();
```
### Demo

More details in demo project.
