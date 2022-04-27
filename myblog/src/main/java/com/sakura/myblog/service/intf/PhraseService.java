package com.sakura.myblog.service.intf;

import com.sakura.myblog.model.entity.Phrase;

import java.util.List;

/**
 * @author Sakura
 */
public interface PhraseService {

    List<Phrase> getPhrase();

    Phrase findPhraseById(int id);

    boolean deletePhrase(int id);

    Phrase updatePhrase(Phrase phrase);

    Phrase addPhrase(Phrase phrase);
}
