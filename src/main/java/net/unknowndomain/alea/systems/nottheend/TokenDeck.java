/*
 * Copyright 2021 m.bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.nottheend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.unknowndomain.alea.random.deck.Deck;

/**
 *
 * @author m.bignami
 */
public class TokenDeck extends Deck<Tokens>
{
    
    private final List<Tokens> content;
    
    public TokenDeck(int white, int black, int random)
    {
        content = new ArrayList<>(white+black+random);
        int i;
        for (i = 0; i < white; i++)
        {
            content.add(Tokens.WHITE);
        }
        for (i = 0; i < black; i++)
        {
            content.add(Tokens.BLACK);
        }
        for (i = 0; i < random; i++)
        {
            int rand = (int) (Math.random() * 10);
            if (rand < 5)
            {
                content.add(Tokens.BLACK);
            }
            else
            {
                content.add(Tokens.WHITE);
            }
        }
    }
    
    public TokenDeck(Tokens ... tokens)
    {
        this(Arrays.asList(tokens));
    }
    
    public TokenDeck(Collection <Tokens> tokens)
    {
        content = new ArrayList<>(tokens.size());
        content.addAll(tokens);
    }

    @Override
    public String getName()
    {
        return "token";
    }

    @Override
    public List<Tokens> getContents()
    {
        return content;
    }
    
}
