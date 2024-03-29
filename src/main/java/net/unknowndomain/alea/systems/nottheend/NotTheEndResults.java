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
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class NotTheEndResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.nottheend.RpgSystemBundle";
    
    private final List<SingleResult<Tokens>> results;
    private final TokenDeck bag;
    private final int whites;
    private final int blacks;
    private NotTheEndResults prev;
    
    public NotTheEndResults(List<SingleResult<Tokens>>  results, TokenDeck bag)
    {
        List<SingleResult<Tokens>>  tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
        this.bag = bag;
        int w = 0 , b = 0;
        for (SingleResult<Tokens> t : results)
        {
            if (t.getValue() == Tokens.WHITE)
            {
                w++;
            }
            else
            {
                b++;
            }
        }
        whites = w;
        blacks = b;
    }

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        messageBuilder.append(indent).append(translate("nottheend.results.white", whites));
        messageBuilder.append(" | ").append(translate("nottheend.results.black", blacks)).appendNewLine();
        
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            if (!results.isEmpty())
            {
                messageBuilder.append(indent).append(translate("nottheend.results.bagResults")).append(" [ ");
                for (SingleResult<Tokens> t : results)
                {
                    messageBuilder.append("( ").append(t.getLabel()).append(" => ");
                    messageBuilder.append(t.getValue()).append(") ");
                }
                messageBuilder.append("]\n");
            }
            if (!bag.getContents().isEmpty())
            {
                messageBuilder.append(indent).append(translate("nottheend.results.bagContent")).append(bag.getContents()).append("\n");
            }
            if (prev != null)
            {
                messageBuilder.append(indent).append(translate("nottheend.results.prevResults")).append(" {\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append(indent).append("}\n");
            }
        }
    }

    public NotTheEndResults getPrev()
    {
        return prev;
    }

    public void setPrev(NotTheEndResults prev)
    {
        this.prev = prev;
    }

    public List<SingleResult<Tokens>> getResults()
    {
        return results;
    }

    public TokenDeck accessBag()
    {
        return bag;
    }

    public int getWhites()
    {
        return whites;
    }

    public int getBlacks()
    {
        return blacks;
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }

}
