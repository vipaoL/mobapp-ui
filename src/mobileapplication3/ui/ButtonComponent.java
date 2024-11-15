/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication3.ui;

import mobileapplication3.platform.Utils;
import mobileapplication3.platform.ui.Font;
import mobileapplication3.platform.ui.Graphics;
import mobileapplication3.platform.ui.RootContainer;

/**
 *
 * @author vipaol
 */
public class ButtonComponent extends AbstractButtonSet {
	
    public ButtonComponent(Button button) {
        this.buttons = new Button[]{button};
    }

    public int getMinPossibleWidth() {
        int res = 0;
        String[] btnTextLines = Utils.split(buttons[0].getTitle(), "\n");
        for (int j = 0; j < btnTextLines.length; j++) {
            res = Math.max(res, Font.getDefaultFont().stringWidth(btnTextLines[j] + "  ") + buttons[0].getBgPagging()*4);
        }
        return res;
    }
    
    public void onSetBounds(int x0, int y0, int w, int h) {
        if (w == W_AUTO) {
            this.w = getMinPossibleWidth();
        }
        if (h == H_AUTO) {
            this.h = Font.getDefaultFont().getHeight() * 5 / 2 + buttonsBgPadding;
        }
    }

    public void onPaint(Graphics g, int x0, int y0, int w, int h, boolean forceInactive) {
    	boolean drawAsSelected = isSelectionVisible && isFocused;
        buttons[0].paint(g, x0, y0, w, h, drawAsSelected, isFocused, forceInactive, showKbHints);
    }

    public boolean handlePointerClicked(int x, int y) {
        if (checkTouchEvent(x, y)) {
            buttons[0].invokePressed(true, isSelectionEnabled);
            return true;
        }
        return false;
    }

    public boolean onKeyPressed(int keyCode, int count) {
        if (isSelectionEnabled && RootContainer.getAction(keyCode) == Keys.FIRE) {
            buttons[0].invokePressed(true, true);
            return true;
        } else {
        	return isFocused;
        }
    }
    
    public boolean handleKeyRepeated(int keyCode, int pressedCount) {
		return false;
	}
    
    public IUIComponent setBgColor(int color) {
    	buttons[0].setBgColor(color);
    	return this;
    }
    
    public ButtonComponent setBindedKeyCode(int keyCode) {
    	buttons[0].setBindedKeyCode(keyCode);
    	return this;
    }
    
    public ButtonComponent setBindedKeyCodes(int[] keyCodes) {
    	buttons[0].setBindedKeyCodes(keyCodes);
    	return this;
    }
    
}
