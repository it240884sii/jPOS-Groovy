/*
 * jPOS-Groovy jPOS based project [http://jpos.org]
 * Copyright (C) 2000-2019 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.jpos.core.ConfigurationException
import org.jpos.iso.ISOSource
import org.jpos.space.Space
import org.jpos.space.SpaceFactory
import org.jpos.space.LocalSpace
import org.jpos.space.SpaceSource
import org.jpos.transaction.Context
import org.jpos.transaction.ContextConstants
import java.util.stream.Collectors

def timeout  = cfg.getLong ("timeout", 15000L);
def sp = (Space<String, Context>) SpaceFactory.getSpace (cfg.get ("space"));
def queue = cfg.get ("queue", null);
if (queue == null)
    throw new ConfigurationException ("queue property not specified");
def src =  cfg.get ("source", ContextConstants.SOURCE.toString());
def request = cfg.get ("request", ContextConstants.REQUEST.toString());
def timestamp = cfg.get ("timestamp", ContextConstants.TIMESTAMP.toString());
def remote = cfg.getBoolean("remote") || cfg.get("space").startsWith("rspace:");

def additionalContextEntries;
Map<String,String> m = new HashMap<>();
cfg.keySet().stream().filter {it.startsWith("ctx.")}.forEach{it -> m.put(it.substring(4), cfg.get(it))};
if (m.size() > 0)  additionalContextEntries = m;

def ctx  = new Context ();
if (remote)
    src = new SpaceSource((LocalSpace)sp, source, timeout);
ctx.put (timestamp, new Date(), remote);
ctx.put (src,  source, remote);
ctx.put (request, message, remote);
if (additionalContextEntries != null)
    additionalContextEntries.entrySet().forEach{it -> ctx.put(it.getKey(), it.getValue(), remote)};

sp.out(queue, ctx, timeout);
return true;