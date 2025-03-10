package folk.sisby.switchy.client.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.nbt.visitor.NbtElementVisitor;
import net.minecraft.nbt.visitor.NbtTextFormatter;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PrettyElementVisitor extends NbtTextFormatter implements NbtElementVisitor {
	public PrettyElementVisitor() {
		super("", 0);
	}

	@SuppressWarnings("DataFlowIssue")
	@Override
	public void visitString(NbtString element) {
		try {
			this.result = Text.Serializer.fromJson(element.asString()).formatted(Formatting.GREEN);
			return;
		} catch (Exception ignored) {}
		Registry.ITEM.getOrEmpty(Identifier.tryParse(element.asString())).ifPresentOrElse(
			i -> this.result = Text.translatable(i.getTranslationKey()).formatted(Formatting.AQUA),
			() -> this.result = Text.literal(element.asString()).formatted(Formatting.GREEN)
		);
	}

	@Override
	public void visitByte(NbtByte element) {
		this.result = Text.literal(String.valueOf(element.byteValue() == 0 ? "no" : (element.byteValue() == 1 ? "yes": element.numberValue()))).formatted(Formatting.GOLD);
	}

	@Override
	public void visitShort(NbtShort element) {
		this.result = Text.literal(String.valueOf(element.numberValue())).formatted(Formatting.GOLD);
	}

	@Override
	public void visitInt(NbtInt element) {
		this.result = Text.literal(String.valueOf(element.numberValue())).formatted(Formatting.GOLD);
	}

	@Override
	public void visitLong(NbtLong element) {
		this.result = Text.literal(String.valueOf(element.numberValue())).formatted(Formatting.GOLD);
	}

	@Override
	public void visitFloat(NbtFloat element) {
		this.result = Text.literal(String.valueOf(element.floatValue())).formatted(Formatting.GOLD);
	}

	@Override
	public void visitDouble(NbtDouble element) {
		this.result = Text.literal(String.valueOf(element.doubleValue())).formatted(Formatting.GOLD);
	}

	@Override
	public void visitCompound(NbtCompound compound) {
		try {
			ItemStack stack = ItemStack.fromNbt(compound);
			MutableText text = Text.empty();
			if (stack.getCount() > 1) text.append(Text.literal(stack.getCount() + " "));
			text.append(stack.getName());
			text.formatted(Formatting.AQUA);
			this.result = text;
			return;
		} catch (Exception ignored) {}
		super.visitCompound(compound);
	}
}
