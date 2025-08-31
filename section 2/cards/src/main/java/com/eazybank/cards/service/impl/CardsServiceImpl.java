package com.eazybank.cards.service.impl;

import com.eazybank.cards.constants.CardsConstants;
import com.eazybank.cards.dto.CardsDto;
import com.eazybank.cards.entity.Cards;
import com.eazybank.cards.exception.CardAlreadyExistsException;
import com.eazybank.cards.exception.ResourceNotFoundException;
import com.eazybank.cards.mapper.CardsMapper;
import com.eazybank.cards.repository.CardsRepository;
import com.eazybank.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    public static final Logger logger = LoggerFactory.getLogger(CardsServiceImpl.class);

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<List<Cards>> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent() && !optionalCards.get().isEmpty()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public List<CardsDto> fetchCard(String correlationId, String mobileNumber) {
        logger.debug("Correlation ID in Card Service: {}", correlationId);

        List<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return cards.stream()
                .map(card -> CardsMapper.mapToCardsDto(card, new CardsDto()))
                .toList();
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        List<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        for (Cards card : cards) {
            cardsRepository.deleteById(card.getCardId());
        }

        return true;
    }


}