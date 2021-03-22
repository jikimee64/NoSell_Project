package com.soap.moon.global.util;

import com.soap.moon.global.util.ValidationGroups.NotEmptyGroup;
import com.soap.moon.global.util.ValidationGroups.PatternCheckGroup;
import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, NotEmptyGroup.class, PatternCheckGroup.class })
public interface ValidationSequence {

}
