package com.jorgegomezdeveloper.crazycards.ui.features.gametable.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.jorgegomezdeveloper.crazycards.R
import com.jorgegomezdeveloper.crazycards.common.Constants
import com.jorgegomezdeveloper.crazycards.data.services.cards.CardsManager
import com.jorgegomezdeveloper.crazycards.data.services.play.PlayManager
import com.jorgegomezdeveloper.crazycards.databinding.FragmentCcGameTableBinding
import com.jorgegomezdeveloper.crazycards.model.CardModel
import com.jorgegomezdeveloper.crazycards.ui.base.CCBaseViewModelFragment
import com.jorgegomezdeveloper.crazycards.ui.features.gametable.viewmodel.CCGameTableViewModel
import kotlinx.android.synthetic.main.card_view_cc_crazy.view.*
import kotlinx.android.synthetic.main.fragment_cc_game_table.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CCGameTableFragment: CCBaseViewModelFragment<CCGameTableViewModel>() {

    companion object {
        const val TAG_FRAGMENT = "CCGameTableFragment"
        const val TIME_FINISH: Long = 5000
        const val TIME_INTERVAL: Long = 1000
        const val TIME_FINISH_END: Long = 10000
    }

    // DEPENDENCY INJECTS

    // View Models and Fragments
    private val ccGameTableViewModel: CCGameTableViewModel by viewModel()

    // Managers
    private val cardsManager: CardsManager by inject()
    private val playManager: PlayManager by inject()

    // View Binding
    private lateinit var binding: FragmentCcGameTableBinding

    override fun getFragmentTag(): String? {
        return TAG_FRAGMENT
    }

    override fun initialize() {
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCcGameTableBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_cc_game_table
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        loadData()
        observeData()
    }

    override fun initializeViews() {

        // Rounds
        counterRoundText.visibility = View.VISIBLE
        updateRounds()

        // Priorities
        ccPrioritiesLayout.visibility = View.VISIBLE
    }

    override fun loadData() {
        ccGameTableViewModel.buildListOrdered(cardsManager)
    }

    private fun observeData() {

        // Results Builders

        ccGameTableViewModel.getBuildListOrderedMutableLiveData().observe(viewLifecycleOwner,
            Observer<ArrayList<CardModel?>> {

                val cardListOrdered = ccGameTableViewModel.getBuildListOrderedMutableLiveData().value
                        as ArrayList<CardModel?>

                ccGameTableViewModel.buildListRandom(cardsManager, cardListOrdered)
            })

        ccGameTableViewModel.getBuildListRandomMutableLiveData().observe(viewLifecycleOwner,
            Observer<ArrayList<CardModel?>> {

                val cardListRandom = ccGameTableViewModel.getBuildListRandomMutableLiveData().value
                        as ArrayList<CardModel?>

                ccGameTableViewModel.buildListRandomLeft(cardsManager, cardListRandom)
            })

        ccGameTableViewModel.getBuildListRandomLeftMutableLiveData().observe(viewLifecycleOwner,
            Observer<ArrayList<CardModel?>> {

                ccGameTableViewModel.buildListRandomRight(
                    cardsManager, cardsManager.getCardListRandom())
            })

        ccGameTableViewModel.getBuildListRandomRightMutableLiveData().observe(viewLifecycleOwner,
            Observer<ArrayList<CardModel?>> {

                initializeViewsPendingCardsUsers()
            })
    }

    private fun initializeViewsPendingCardsUsers() {

        // User Left
        cardsPendingUserLeftImage.isEnabled = true
        cardsPendingUserLeftImage.visibility = View.VISIBLE
        cardsPendingUserLeftImage.ccCrazyFrontLayout.visibility = View.GONE
        cardsPendingUserLeftImage.ccCrazyBackLayout.visibility = View.VISIBLE
        cardPutUserLeftImage.visibility = View.GONE
        messageStartGameLeftText.visibility = View.VISIBLE
        cardsDiscardsUserLeftImage.visibility = View.INVISIBLE
        counterDiscardsUserLeftText.visibility = View.INVISIBLE

        // User Right
        cardsPendingUserRightImage.isEnabled = true
        cardsPendingUserRightImage.visibility = View.VISIBLE
        cardsPendingUserRightImage.ccCrazyFrontLayout.visibility = View.GONE
        cardsPendingUserRightImage.ccCrazyBackLayout.visibility = View.VISIBLE
        cardPutUserRightImage.visibility = View.GONE
        messageStartGameRightText.visibility = View.VISIBLE
        cardsDiscardsUserRightImage.visibility = View.INVISIBLE
        counterDiscardsUserRightText.visibility = View.INVISIBLE

        initializeListenersPendingCardsUsers()
    }

    private fun initializeListenersPendingCardsUsers() {

        // User Left
        cardsPendingUserLeftImage.setOnClickListener {
            initializeViewsCardPutUserLeft(cardsManager.getCardPutUserLeft())
        }

        // User Right
        cardsPendingUserRightImage.setOnClickListener {
            initializeViewsCardPutUserRight(cardsManager.getCardPutUserRight())
        }
    }

    private fun initializeViewsCardPutUserLeft(card: CardModel?) {

        cardsPendingUserLeftImage.isEnabled = false
        if (playManager.getCounterRounds() == PlayManager.MAX_ROUNDS) {
            cardsPendingUserLeftImage.visibility = View.INVISIBLE
        }

        messageStartGameLeftText.visibility = View.GONE
        cardPutUserLeftImage.visibility = View.VISIBLE
        cardPutUserLeftImage.ccCrazyFrontLayout.visibility = View.VISIBLE
        cardPutUserLeftImage.ccCrazyBackLayout.visibility = View.GONE

        cardPutUserLeftImage.ccCrazyFrontLayout.cvNumberCardText.text = card?.symbol
        cardPutUserLeftImage.ccCrazyFrontLayout
            .cvTypeCrazyImage.setImageDrawable(getImageCrazyCard(card?.type))

        playManager.setPutCardLeft(true)
        ccGameTableViewModel.cardLefPut = card
        if (playManager.areTwoCardsUsersPut()) initializeControlResult()
    }

    private fun initializeViewsCardPutUserRight(card: CardModel?) {

        cardsPendingUserRightImage.isEnabled = false
        if (playManager.getCounterRounds() == PlayManager.MAX_ROUNDS) {
            cardsPendingUserRightImage.visibility = View.INVISIBLE
        }

        messageStartGameRightText.visibility = View.GONE
        cardPutUserRightImage.visibility = View.VISIBLE
        cardPutUserRightImage.ccCrazyFrontLayout.visibility = View.VISIBLE
        cardPutUserRightImage.ccCrazyBackLayout.visibility = View.GONE

        cardPutUserRightImage.ccCrazyFrontLayout.cvNumberCardText.text = card?.symbol
        cardPutUserRightImage.ccCrazyFrontLayout
            .cvTypeCrazyImage.setImageDrawable(getImageCrazyCard(card?.type))

        playManager.setPutCardRight(true)
        ccGameTableViewModel.cardRightPut = card
        if (playManager.areTwoCardsUsersPut()) initializeControlResult()
    }

    private fun getImageCrazyCard(type: Int?): Drawable? {

        var imageCrazy: Drawable? = null

        when (type) {
            //Original
            1 -> {
                imageCrazy = ContextCompat.getDrawable(requireContext(), R.drawable.ic_crazy_original)}
            //Party
            2 -> {
                imageCrazy = ContextCompat.getDrawable(requireContext(), R.drawable.ic_crazy_party)}
            //Angry
            3 -> {
                imageCrazy = ContextCompat.getDrawable(requireContext(), R.drawable.ic_crazy_angry)}
            //Covid
            4 -> {
                imageCrazy = ContextCompat.getDrawable(requireContext(), R.drawable.ic_crazy_covid)}
        }

        return imageCrazy
    }

    private fun initializeControlResult() {

        val userWinner = playManager
            .compareCards(ccGameTableViewModel.cardLefPut!!, ccGameTableViewModel.cardRightPut!!)

        if (userWinner == Constants.WIN_USER_LEFT) {

            cardsManager.addCardsListDiscardsUserLeft(
                ccGameTableViewModel.cardLefPut!!, ccGameTableViewModel.cardRightPut!!)

            counterDiscardsUserLeftText.visibility = View.VISIBLE
            counterDiscardsUserLeftText.text = getString(R.string.text_win_round)
            counterDiscardsUserRightText.visibility = View.VISIBLE
            counterDiscardsUserRightText.text = getString(R.string.text_lose_round)

        } else {

            cardsManager.addCardsListDiscardsUserRight(
                ccGameTableViewModel.cardLefPut!!, ccGameTableViewModel.cardRightPut!!)

            counterDiscardsUserRightText.visibility = View.VISIBLE
            counterDiscardsUserRightText.text = getString(R.string.text_win_round)
            counterDiscardsUserLeftText.visibility = View.VISIBLE
            counterDiscardsUserLeftText.text = getString(R.string.text_lose_round)
        }

        startTimer(userWinner)
    }

    private fun startTimer(userWinner: String?) {

        val countDownTimer: CountDownTimer = object : CountDownTimer(TIME_FINISH, TIME_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                cancel()
                initializeViewsCardsDiscardsUsers(userWinner)
            }
        }
        countDownTimer.start()
    }

    private fun initializeViewsCardsDiscardsUsers(userWinner: String?) {

        messageStartGameLeftText.visibility = View.VISIBLE
        cardPutUserLeftImage.visibility = View.GONE
        cardsPendingUserLeftImage.isEnabled = true
        messageStartGameRightText.visibility = View.VISIBLE
        cardPutUserRightImage.visibility = View.GONE
        cardsPendingUserRightImage.isEnabled = true

        if (userWinner == Constants.WIN_USER_LEFT) {

            // User Left
            cardsDiscardsUserLeftImage.visibility = View.VISIBLE
            cardsDiscardsUserLeftImage.ccCrazyFrontLayout.visibility = View.GONE
            cardsDiscardsUserLeftImage.ccCrazyBackLayout.visibility = View.VISIBLE

            playManager.countRWonUserLeft()
            playManager.countDiscardsUserLeft()
            counterDiscardsUserLeftText.text =
                getString(
                    R.string.text_result_discards,
                    playManager.getCounterDiscardsUserLeft().toString())

            counterDiscardsUserRightText.text =
                getString(
                    R.string.text_result_discards,
                    playManager.getCounterDiscardsUserRight().toString())

        } else {

            // User Right
            cardsDiscardsUserRightImage.visibility = View.VISIBLE
            cardsDiscardsUserRightImage.ccCrazyFrontLayout.visibility = View.GONE
            cardsDiscardsUserRightImage.ccCrazyBackLayout.visibility = View.VISIBLE

            playManager.countRWonUserRight()
            playManager.countDiscardsUserRight()
            counterDiscardsUserRightText.text =
                getString(
                    R.string.text_result_discards,
                    playManager.getCounterDiscardsUserRight().toString())

            counterDiscardsUserLeftText.text =
                getString(
                    R.string.text_result_discards,
                    playManager.getCounterDiscardsUserLeft().toString())
        }

        updateRounds()
    }

    private fun updateRounds() {

        if (playManager.getCounterRounds() < PlayManager.MAX_ROUNDS) {

            playManager.countRound()
            counterRoundText.text =
                getString(R.string.text_counter_round, playManager.getCounterRounds().toString())
            titleRoundText.visibility = View.VISIBLE

            playManager.setPutCardLeft(false)
            playManager.setPutCardRight(false)

        } else {
            finalizePlay()
        }
    }

    private fun finalizePlay() {

        messageStartGameLeftText.textSize = 20f
        messageStartGameRightText.textSize = 20f

        if (playManager.getCounterRWonUserLeft() > playManager.getCounterRWonUserRight()) {
            messageStartGameLeftText.text = getString(R.string.text_winner_game)
            messageStartGameRightText.text = getString(R.string.text_loser_game)
        } else {
            messageStartGameLeftText.text = getString(R.string.text_loser_game)
            messageStartGameRightText.text = getString(R.string.text_winner_game)
        }

        startTimerFinalize()
    }

    private fun startTimerFinalize() {

        val countDownTimer: CountDownTimer = object : CountDownTimer(TIME_FINISH_END, TIME_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                cancel()
                resetGame()
            }
        }
        countDownTimer.start()
    }

    private fun resetGame() {

        messageStartGameLeftText.textSize = 12f
        messageStartGameRightText.textSize = 12f
        messageStartGameLeftText.text = getString(R.string.message_start_game)
        messageStartGameRightText.text = getString(R.string.message_start_game)
        cardsManager.freeResources()
        playManager.freeResources()
        initializeViews()
        loadData()
        observeData()
    }
}