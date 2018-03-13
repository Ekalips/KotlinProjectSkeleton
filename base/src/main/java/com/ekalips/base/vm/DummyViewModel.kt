package com.ekalips.base.vm

import com.ekalips.base.state.BaseViewState
import javax.inject.Inject

/**
 * Created by Ekalips on 2/7/18.
 */


class DummyViewModel @Inject constructor() : BaseViewModel<BaseViewState>(){
    override val state: BaseViewState = BaseViewState()
}
