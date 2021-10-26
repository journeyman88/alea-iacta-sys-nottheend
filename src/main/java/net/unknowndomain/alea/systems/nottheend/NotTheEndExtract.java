/*
 * Copyright 2020 Marco Bignami.
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
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.GenericResult;

/**
 *
 * @author journeyman
 */
public class NotTheEndExtract extends NotTheEndBase
{
    private final TokenDeck bag;
    private final int extract;
    
    public NotTheEndExtract(Integer trait, Integer difficulty, Integer random, Integer extract, NotTheEndModifiers ... mod)
    {
        this(trait, difficulty, extract, random, Arrays.asList(mod));
    }
    
    public NotTheEndExtract(Integer traits, Integer difficulty, Integer random, Integer extract, Collection<NotTheEndModifiers> mod)
    {
        super(mod);
        int w = 0, b = 0, r = 0;
        if (traits != null)
        {
            w = traits;
        }
        if (difficulty != null)
        {
            b = difficulty;
        }
        if (random != null)
        {
            r = random;
        }
        bag = new TokenDeck(w, b, r);
        if (extract > 4)
        {
            extract = 4;
        }
        if (extract < 1)
        {
            extract = 1;
        }
        this.extract = extract;
    }

    @Override
    protected TokenDeck getBag()
    {
        return bag;
    }
    
    @Override
    public GenericResult getResult()
    {
        List<SingleResult<Tokens>> result = new ArrayList<>(extract);
        NotTheEndResults results = extractTokens(result, extract);
        return results;
    }
    
}
