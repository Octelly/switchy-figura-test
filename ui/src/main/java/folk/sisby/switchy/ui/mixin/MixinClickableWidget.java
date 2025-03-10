package folk.sisby.switchy.ui.mixin;

import folk.sisby.switchy.ui.util.SwitchyOwoUtil;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClickableWidget.class)
public class MixinClickableWidget {
	@Shadow protected boolean hovered;

	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ClickableWidget;renderButton(Lnet/minecraft/client/util/math/MatrixStack;IIF)V"))
	public void dontHoverUnderOverlays(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (hovered) hovered = SwitchyOwoUtil.componentCanHover((Component) this);
	}
}
