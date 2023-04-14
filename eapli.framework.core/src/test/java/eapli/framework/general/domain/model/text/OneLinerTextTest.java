/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.framework.general.domain.model.text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public class OneLinerTextTest extends TextTestBase {

	public static final String ONE_LINER = "a little red fox jumps over a fence.";

	@Override
	public String getText() {
		return ONE_LINER;
	}

	@Override
	public long getExpectedWordCount() {
		return 8;
	}

	@Override
	public long getExpectedLineCount() {
		return 1;
	}

	@Override
	public List<String> getExpectedWords() {
		final String[] words = { "a", "little", "red", "fox", "jumps", "over", "a", "fence" };
		return Arrays.asList(words);
	}

	@Override
	public Map<String, Integer> getExpectedFrequency() {
		final Map<String, Integer> freq = new HashMap<>();
		freq.put("a", 2);
		freq.put("little", 1);
		freq.put("red", 1);
		freq.put("fox", 1);
		freq.put("jumps", 1);
		freq.put("over", 1);
		freq.put("fence", 1);
		return freq;
	}
}